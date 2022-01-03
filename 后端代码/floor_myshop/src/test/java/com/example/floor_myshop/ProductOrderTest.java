package com.example.floor_myshop;

import com.example.floor_myshop.conditon.OrderCondition;
import com.example.floor_myshop.entity.ProductOrder;
import com.example.floor_myshop.mapper.ProductOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author tong
 * @date 2021/12/24
 */
@SpringBootTest
public class ProductOrderTest {

    @Autowired
    private ProductOrderMapper mapper;

    @Test
    public void testGet(){
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setOrderId(12);
//        orderCondition.setMinAmount(1);
//        orderCondition.setMaxAmount(2);

        final List<ProductOrder> list = mapper.getProductOrderListByCondition(orderCondition);
        System.out.println(list);
    }



}
