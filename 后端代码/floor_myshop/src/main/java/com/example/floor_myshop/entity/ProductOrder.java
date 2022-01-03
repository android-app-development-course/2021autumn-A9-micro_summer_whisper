package com.example.floor_myshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.floor_myshop.vo.OrderVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@TableName("product_order")
@Setter
@Getter
@ToString
@AllArgsConstructor
public class ProductOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final Integer WAIT_PAY = 1;
    public static final Integer WAIT_SEND_GOOD = 2;
    public static final Integer WAIT_CONFIRM_RECEIVE = 3;
    public static final Integer FINISHED = 4;


    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private Integer productId;

    private Integer shopId;
    private String address;

    /**
     * 订单状态
     *
     */
    private Integer logisticsStatus;
    private Integer logisticsId;

    private Integer userId;

    private Integer isDeleted;

    private LocalDateTime createTime;

    private Integer orderState;
    private Integer amount;
    private String receiverName;
    private String receiverPhone;

    private String logisticsOrderNumber;

    public OrderVo toOrderVo(String shopName, String shopImg,  Integer onePrice, Integer totalPrice,
             String orderStateText){
        return new OrderVo(orderId,productId,shopId,logisticsStatus,logisticsId,userId,isDeleted,createTime,shopName,shopImg,
                onePrice,totalPrice,amount,orderState,orderStateText,receiverName,receiverPhone,address,logisticsOrderNumber);
    }



}
