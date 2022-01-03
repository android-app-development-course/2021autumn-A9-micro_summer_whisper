package com.example.floor_myshop.vo;

import com.example.floor_myshop.entity.Category;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Setter
@Getter
@ToString
public class CategoryVo implements Serializable {

    private Integer categoryId;

    private String categoryName;

    private String categoryDesc;

    private Integer priority;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;

    private Integer shopId;

    private Integer isDeleted;

    public Category toCategory(){
        return new Category(categoryId,categoryName,categoryDesc, priority, createTime, lastEditTime,
                shopId, isDeleted
                );
    }

}
