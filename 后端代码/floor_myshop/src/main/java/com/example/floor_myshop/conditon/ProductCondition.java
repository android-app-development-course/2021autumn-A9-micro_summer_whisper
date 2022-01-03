package com.example.floor_myshop.conditon;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author tong
 * @date 2021/12/23
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductCondition {

    private Integer productId;

    private Integer categoryId;

    private String categoryName;

    private String productName;

    private String productDesc;

    private Integer minNormalPrice;
    private Integer maxNormalPrice;

    private Integer minPromotionPrice;
    private Integer maxPromotionPrice;

    private Integer minPriority;
    private Integer maxPriority;

    private LocalDateTime minCreateTime;
    private LocalDateTime maxCreateTime;

    private LocalDateTime minLastEditTime;
    private LocalDateTime maxLastEditTime;

    private Integer enableStatus;

    private Integer shopId;

    private Integer isDeleted;
    private Integer minStock;
    private Integer maxStock;




}
