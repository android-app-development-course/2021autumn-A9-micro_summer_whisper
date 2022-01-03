package com.example.floor_myshop;

import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.entity.Product;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;



@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private IProductService productService;

   @Test
   void testRegister() {
//       Account account = new Account();
//       account.setUserName("test5");
//       account.setPassword("test5");
//       account.setCreateTime(LocalDateTime.now());
//       HttpEntity<Account> entity = new HttpEntity<>(account);
//       ResponseEntity<ApiResponse> response = restTemplate.exchange(BASE_URL + "account/register", HttpMethod.POST, entity, ApiResponse.class);
//       ApiResponse body = response.getBody();
//       System.out.println(body.getData());

   }

    @Test
    public void testAddProduct(){
        final Product product = new Product();
        product.setProductName("玫瑰11");
        product.setCategoryId(13);
        product.setShopId(12);
        final boolean save = productService.save(product);
        System.out.println(product);

    }




}
