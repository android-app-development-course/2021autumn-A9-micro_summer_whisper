package com.example.floor_myshop.controller;


import com.example.floor_myshop.entity.Science;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IScienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/science")
public class ScienceController {
    @Autowired
    IScienceService service;
    @GetMapping("/all")
    public ApiResponse scienceAll() {
        List<Science> list = service.list();
        return ApiResponse.success("data",list);
    }

}

