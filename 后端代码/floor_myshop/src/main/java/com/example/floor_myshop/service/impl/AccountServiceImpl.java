package com.example.floor_myshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.mapper.AccountMapper;
import com.example.floor_myshop.mapper.PersonMapper;
import com.example.floor_myshop.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    private static final Logger log1 = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    PersonMapper personMapper;

    @Override
    public Person register(Account account) {
        int insert = accountMapper.insert(account);
        log1.info("account:{}",account);
        Person person = new Person();
        person.setUserId(account.getAccountId());
        person.setName(account.getUserName());
        personMapper.insert(person);
        return person;
    }

    @Override
    public Account findByAc(Account account) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",account.getUserName());
        queryWrapper.eq("password",account.getPassword());
        return accountMapper.selectOne(queryWrapper);
    }
}

