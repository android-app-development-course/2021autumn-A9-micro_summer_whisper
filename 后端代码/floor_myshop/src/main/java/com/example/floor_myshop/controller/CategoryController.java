package com.example.floor_myshop.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.floor_myshop.conditon.CategoryCondition;
import com.example.floor_myshop.entity.Category;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.ICategoryService;
import com.example.floor_myshop.util.ControllerUtils;
import com.example.floor_myshop.vo.CategoryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/getCategoryList")
    public ApiResponse getCategoryList(@RequestBody CategoryCondition categoryCondition){
        try {
            final List<CategoryVo> categoryVos;
            Map<SFunction<Category,?>, Object> map = new HashMap<>();
            map.put(Category::getCategoryId,categoryCondition.getCategoryId());
            map.put(Category::getShopId,categoryCondition.getShopId());
            map.put(Category::getIsDeleted,categoryCondition.getIsDeleted());
            if (categoryCondition.getIsExactCategoryName()!=null&& categoryCondition.getIsExactCategoryName()){
                map.put(Category::getCategoryName,categoryCondition.getCategoryName());
            } else {
                categoryCondition.setIsExactCategoryName(false);
            }
            final List<Category> list = categoryService.list(Wrappers.<Category>lambdaQuery()
                    .like(!categoryCondition.getIsExactCategoryName() && StringUtils.isNotBlank(categoryCondition.getCategoryName()), Category::getCategoryName, categoryCondition.getCategoryName())
                    .like(StringUtils.isNotBlank(categoryCondition.getCategoryDesc()), Category::getCategoryDesc, categoryCondition.getCategoryDesc())
                    .between(ControllerUtils.checkALessThanB(categoryCondition.getMinPriority(), categoryCondition.getMaxPriority(), 0),
                            Category::getPriority, categoryCondition.getMinPriority(), categoryCondition.getMaxPriority())
                    .allEq(map,false)
            );
            categoryVos = Category.toCategoryVoList(list);
            return ApiResponse.success("获取分类列表成功",categoryVos);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.failed("获取分类列表失败");
        }
    }

    @PostMapping("/addCategory")
    public ApiResponse addCategory(@RequestBody CategoryVo categoryVo){
        final Category category = categoryVo.toCategory();
        if (categoryService.save(category)) {
            return ApiResponse.success("添加分类成功",category.toCategoryVo());
        } else {
            return ApiResponse.failed("添加分类失败");
        }
    }

    @PostMapping("/updateCategory")
    public ApiResponse updateCategory(@RequestBody CategoryVo categoryVo){
        final Category category = categoryVo.toCategory();
        if (categoryService.updateById(category)) {
            return ApiResponse.success("更新分类成功",category.toCategoryVo());
        } else {
            return ApiResponse.failed("更新分类失败");
        }
    }

}

