package com.example.floor_myshop.mapper;

import com.example.floor_myshop.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
