package com.example.floor_myshop;

import com.example.floor_myshop.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tong
 * @date 2021/12/23
 */
@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void getToken(){
        final String token = jwtUtil.createJWT("666666", "hi", "merchant", 12);
        System.out.println(token);
    }

}
