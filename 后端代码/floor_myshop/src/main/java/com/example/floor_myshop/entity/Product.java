package com.example.floor_myshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.floor_myshop.vo.ProductVo;
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
@Getter
@Setter
@ToString
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    private Integer categoryId;

    private String productName;

    private String productDesc;

    private String imgAddr;

    private Integer normalPrice;

    private Integer promotionPrice;

    private Integer priority;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;

    private Integer enableStatus;

    private Integer shopId;

    private Integer isDeleted;
    private Integer stock;
    private String pictureA;
    private String pictureB;
    private String pictureC;
    private String pictureD;
    private String pictureE;
    private String pictureF;

    public ProductVo toProductVo(String categoryName){
        return new ProductVo(productId,categoryId,categoryName,productName,productDesc,imgAddr,normalPrice,promotionPrice,priority,
                createTime,lastEditTime,enableStatus,shopId,isDeleted,stock,pictureA,pictureB,pictureC,pictureD,
                pictureE,pictureF
        );
    }

}
