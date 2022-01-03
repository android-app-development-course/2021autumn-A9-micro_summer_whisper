package com.example.floor_myshop.conditon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tong
 * @date 2021/12/24
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Alias("orderCondition")
public class OrderCondition implements Serializable {



    private Integer orderId;

    private Integer productId;

    private Integer shopId;

    /**
     * 订单状态
     *
     */
    private Integer logisticsStatus;

    private Integer logisticsId;

    private Integer userId;

    private Integer isDeleted;

    private LocalDateTime minCreateTime;
    private LocalDateTime maxCreateTime;

    private String shopName;

    private String shopImg;
    private Integer minOnePrice;
    private Integer maxOnePrice;
    private Integer minTotalPrice;
    private Integer maxTotalPrice;
    private Integer minAmount;
    private Integer maxAmount;
    private Integer orderState;
    private String orderStateText;
    private String receiverName;
    private String receiverPhone;
    private String address;
    private String logisticsOrderNumber;


}
