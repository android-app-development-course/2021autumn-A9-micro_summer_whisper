package com.example.floor_myshop.service.impl;

import com.example.floor_myshop.entity.Product;
import com.example.floor_myshop.mapper.ProductMapper;
import com.example.floor_myshop.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
