package com.example.floor_myshop.vo;

import com.example.floor_myshop.entity.ProductOrder;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author tong
 * @date 2021/12/23
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {

    public static final Integer ALL = 0;
    public static final Integer WAIT_PAY = 1;
    public static final Integer WAIT_SEND_GOOD = 2;
    public static final Integer WAIT_CONFIRM_RECEIVE = 3;
    public static final Integer FINISHED = 4;

    private Integer orderId;

    private Integer productId;

    private Integer shopId;

    private Integer logisticsStatus;

    private Integer logisticsId;

    private Integer userId;

    private Integer isDeleted;

    private LocalDateTime createTime;

    private String productName;
    private String productImg;
    private Integer onePrice;
    private Integer totalPrice;
    private Integer amount;
    private Integer orderState;
    private String orderStateText;
    private String receiverName;
    private String receiverPhone;
    private String address;
    private String logisticsOrderNumber;

    public ProductOrder toProductOrder(){
        return new ProductOrder(orderId,productId,shopId,address,logisticsStatus,logisticsId,userId,isDeleted,createTime,orderState,
                amount,receiverName,receiverPhone,logisticsOrderNumber
                );
    }

}
