package com.example.floor_myshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.floor_myshop.conditon.OrderCondition;
import com.example.floor_myshop.entity.ProductOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */

public interface ProductOrderMapper extends BaseMapper<ProductOrder> {

//    List<ProductOrder> getProductOrderListByCondition(Integer id);
    List<ProductOrder> getProductOrderListByCondition(OrderCondition orderCondition);

}
