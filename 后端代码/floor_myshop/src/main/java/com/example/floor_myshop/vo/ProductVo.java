package com.example.floor_myshop.vo;

import com.example.floor_myshop.entity.Product;
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
public class ProductVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;

    private Integer categoryId;

    private String categoryName;

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

    public Product toProduct(){
        return new Product(productId,categoryId,productName,productDesc,imgAddr,normalPrice,promotionPrice,priority,
                createTime,lastEditTime,enableStatus,shopId,isDeleted,stock,pictureA,pictureB,pictureC,pictureD,pictureE,pictureF);
    }

}
