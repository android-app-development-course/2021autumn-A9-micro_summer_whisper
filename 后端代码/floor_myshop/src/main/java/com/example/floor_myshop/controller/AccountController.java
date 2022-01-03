package com.example.floor_myshop.controller;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.model.EmailModel;
import com.example.floor_myshop.service.IAccountService;
import com.example.floor_myshop.service.IPersonService;
import com.example.floor_myshop.util.JwtUtil;
import com.example.floor_myshop.util.MailSendUtils;
import com.example.floor_myshop.vo.AccountVo;
import com.example.floor_myshop.vo.PersonVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService service;

    @Autowired
    private IPersonService personService;

    private String code;

    private Map<String, String> ip2code = new ConcurrentHashMap<>();

    @Autowired
    private MailSendUtils mailSendUtils;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PostMapping("/code")
    public ApiResponse getCode(@RequestBody Account account) {
        Random random = new Random();
        String temp = "";
        for (int i = 0; i < 5; i++) {
            temp+=String.valueOf(random.nextInt(10));
        }
        code = temp;
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTheme("校验码");
        emailModel.setRecieverName(account.getUserName());
        emailModel.setEmailContent(code);
        emailModel.setRecieverEmailAddress(account.getUserName());
        mailSendUtils.sendCode(emailModel);
        return ApiResponse.success("已发送code");
    }


    @PostMapping("/register")
    public ApiResponse register(@RequestBody AccountVo account){
        if (!StringUtils.equals(code,account.getCd())){
            return ApiResponse.failed("校验码错误",500);
        }
        Account ac = new Account();
        BeanUtils.copyProperties(account,ac);
        Person p = service.register(ac);
        if (ObjectUtils.isEmpty(p)) {
            return ApiResponse.failed("注册失败",503);
        }
        else{
            return ApiResponse.success("成功注册",p);
        }
    }


    @PostMapping("/login")
    public ApiResponse login(@RequestBody Account account) {
        Account accountDb = service.findByAc(account);
        if (ObjectUtils.isEmpty(accountDb)) return ApiResponse.failed("账号名或者密码错误",503);
        Person byId = personService.getById(accountDb.getAccountId());
        final String token = jwtUtil.createJWT(byId.getUserId().toString(), "login", "merchant_claims", byId.getUserId());
        return ApiResponse.success(byId);
    }



    @PostMapping("/tLoginByPassword")
    public ApiResponse tLoginByPassword(@RequestBody PersonVo personVo){
        final IAccountService accountService = service;
        Map<SFunction<Account,?>, Object> map = new HashMap<>();
        map.put(Account::getUserName,personVo.getUserName());
        map.put(Account::getPassword,personVo.getPassword());
        final Account one = accountService.getOne(Wrappers.<Account>lambdaQuery()
                .allEq(map)
        );
        final Person p = personService.getById(one.getUserId());
        final String token = jwtUtil.createJWT(p.getUserId().toString(), p.getUserId().toString(), "merchant", p.getUserId());
        return ApiResponse.success("登录成功",PersonVo.toPersonVo(one,p,token));
    }

    @PostMapping("/tLoginByCode")
    public ApiResponse tLoginByCode(@RequestBody PersonVo personVo, HttpServletRequest request){
        final String code = ip2code.get(request.getRemoteHost());
        if (code==null){
            return ApiResponse.failed("未请求验证码");
        }
        if (!code.equals(personVo.getCode())){
            return ApiResponse.failed("验证码不正确");
        }
        ip2code.remove(request.getRemoteHost());
        final IAccountService accountService = service;
        Map<SFunction<Account,?>, Object> map = new HashMap<>();
        map.put(Account::getUserName,personVo.getUserName());
        final Account one = accountService.getOne(Wrappers.<Account>lambdaQuery()
                .allEq(map)
        );
        final Person p = personService.getById(one.getUserId());
        final String token = jwtUtil.createJWT(p.getUserId().toString(), p.getUserId().toString(), "merchant", p.getUserId());
        return ApiResponse.success("登录成功",PersonVo.toPersonVo(one,p,token));
    }


    @GetMapping("/tCode")
    public ApiResponse tCode(HttpServletRequest request, @RequestParam("email") String email){
        Random random = new Random();
        String temp = "";
        for (int i = 0; i < 5; i++) {
            temp+=String.valueOf(random.nextInt(10));
        }
        code = temp;
        ip2code.put(request.getRemoteHost(), temp);
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTheme("校验码");
        emailModel.setRecieverName(email);
        emailModel.setEmailContent(code);
        emailModel.setRecieverEmailAddress(email);
        mailSendUtils.sendCode(emailModel);
        return ApiResponse.success("已发送code");
    }


    @PostMapping("/tRegister")
    public ApiResponse tRegister(@RequestBody PersonVo personVo, HttpServletRequest request){
        final String code = ip2code.get(request.getRemoteHost());
        if (code==null){
            return ApiResponse.failed("未请求验证码");
        }
        if (!code.equals(personVo.getCode())){
            return ApiResponse.failed("验证码不正确");
        }
        ip2code.remove(request.getRemoteHost());
        final IAccountService accountService = service;
        final Account account = personVo.toAccount();
        final Person[] person = {null};
        final Boolean success = transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    if (accountService.save(account)) {
                        account.setUserId(account.getAccountId());
                        accountService.updateById(account);
                        personVo.setUserId(account.getUserId());
                        personVo.setEmail(personVo.getUserName());
                        personVo.setName(personVo.getUserName());
                        person[0] = personVo.toPerson();
                        if (personService.save(person[0])) {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    status.setRollbackOnly();
                }
                return false;
            }
        });
        if (Boolean.TRUE.equals(success)){
            return ApiResponse.success("注册成功",PersonVo.toPersonVo(account, person[0],""));
        } else {
            return ApiResponse.failed("注册失败");
        }
    }


}

