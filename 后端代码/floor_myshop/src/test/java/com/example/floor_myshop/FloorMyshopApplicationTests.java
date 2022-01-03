package com.example.floor_myshop;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.mapper.AccountMapper;
import com.example.floor_myshop.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

@SpringBootTest
class FloorMyshopApplicationTests {
    @Autowired
    PersonMapper personMapper;
    @Test
    void contextLoads() {
    }
    @Test
    void generator() {
        FastAutoGenerator.create("jdbc:mysql://159.75.211.245:3306/floor_shop?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false", "pwh", "123456")
                .globalConfig(builder -> builder.outputDir("/home/pwh/IdeaProjects/floor_myshop/src/main/java").author("coderpwh"))
                .strategyConfig(builder -> builder.addInclude("person","shop","category","product"
                        ,"product_order","head_line","account","diary","science"))
                .packageConfig(builder -> builder.parent("com.example.floor_myshop"))
                .execute();

    }

    @Test
    void testMapper() {
        Person person = new Person();
        person.setName("tom");
        person.setPhone("1100221");
        person.setCreateTime(LocalDateTime.now());
        person.setLastEditTime(LocalDateTime.now());
        person.setEmail("fjeojf@qq.com");
        person.setGender("男");
        person.setProfileImg("fjeo.jpg");
        person.setAdminFlag(1);

        personMapper.insert(person);
    }

}
