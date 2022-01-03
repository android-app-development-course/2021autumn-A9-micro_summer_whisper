package com.example.floor_myshop.service.impl;

import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.mapper.PersonMapper;
import com.example.floor_myshop.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
