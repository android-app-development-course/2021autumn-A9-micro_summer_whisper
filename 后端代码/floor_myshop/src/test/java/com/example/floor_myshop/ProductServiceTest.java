package com.example.floor_myshop;

import com.example.floor_myshop.entity.Product;
import com.example.floor_myshop.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author tong
 * @date 2021/12/22
 */
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private IProductService productService;

    @Test
    public void testUpdate(){
        final boolean b = productService.updateById(new Product(19, null, null, "你好",
                null, null, null, null, null
                , null, null, null, null ,
                null, null, null, null,null,null,null));
        assert b == true;
    }


    @Test
    public void testList(){
        final List<Product> list = productService.list();
        System.out.println(list);
    }



}
