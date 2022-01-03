package com.example.floor_myshop.conditon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@Data
public class CategoryCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer categoryId;

    @JsonProperty(defaultValue = "false")
    private Boolean isExactCategoryName;

    private String categoryName;

    private String categoryDesc;

    private Integer minPriority;
    private Integer maxPriority;

    private Integer shopId;

    private Integer isDeleted;



}
