package com.example.floor_myshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.floor_myshop.entity.Diary;
import com.example.floor_myshop.mapper.DiaryMapper;
import com.example.floor_myshop.service.IDiaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements IDiaryService {

}
