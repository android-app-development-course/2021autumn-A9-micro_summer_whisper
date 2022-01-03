package com.example.floor_myshop.service.impl;

import com.example.floor_myshop.entity.Category;
import com.example.floor_myshop.mapper.CategoryMapper;
import com.example.floor_myshop.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
