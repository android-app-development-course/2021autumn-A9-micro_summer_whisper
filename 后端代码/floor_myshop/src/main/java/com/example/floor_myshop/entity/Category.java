package com.example.floor_myshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.floor_myshop.vo.CategoryVo;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
* @author coderpwh
 * @since 2021-12-19
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    private String categoryName;

    private String categoryDesc;

    private Integer priority;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;

    private Integer shopId;

    private Integer isDeleted;


    public CategoryVo toCategoryVo(){
        return new CategoryVo(categoryId,categoryName,categoryDesc,priority,createTime,lastEditTime,
                shopId,isDeleted);
    }

    public static List<CategoryVo> toCategoryVoList(List<Category> categoryList){
        final List<CategoryVo> collect = categoryList.stream().map(new Function<Category, CategoryVo>() {
            @Override
            public CategoryVo apply(Category category) {
                return category.toCategoryVo();
            }
        }).collect(Collectors.toList());
        return collect;
    }


}
