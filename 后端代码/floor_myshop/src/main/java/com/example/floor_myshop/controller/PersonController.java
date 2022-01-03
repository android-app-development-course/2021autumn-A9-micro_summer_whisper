package com.example.floor_myshop.controller;


import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IAccountService;
import com.example.floor_myshop.service.IPersonService;
import com.example.floor_myshop.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    IPersonService personService;

    @Autowired
    IAccountService accountService;

    @GetMapping("info")
    public ApiResponse info(@RequestParam Integer id) {
        Person byId = personService.getById(id);
        return ApiResponse.success(byId);
    }
    @PostMapping("update")
    public ApiResponse update(@RequestBody Person p ) {
        personService.updateById(p);
        return ApiResponse.success("update success");
    }


    @GetMapping("tInfo")
    public ApiResponse tInfo(@RequestParam Integer id) {
        Person person = personService.getById(id);
        final Account account = accountService.getById(id);
        return ApiResponse.success(PersonVo.toPersonVo(account,person,""));
    }

    @PostMapping("tUpdate")
    public ApiResponse tUpdate(@RequestBody PersonVo p ) {
        p.setUserName(null);
        final Person person = p.toPerson();
        personService.updateById(person);
        final Account account = p.toAccount();
        accountService.updateById(account);
        return ApiResponse.success("update success");
    }

}

