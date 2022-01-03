package com.example.floor_myshop.service;

import com.example.floor_myshop.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.floor_myshop.entity.Person;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
public interface IAccountService extends IService<Account> {
    Person register(Account account);

    Account findByAc(Account account);
}
