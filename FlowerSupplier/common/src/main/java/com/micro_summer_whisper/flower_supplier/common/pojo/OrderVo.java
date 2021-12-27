package com.micro_summer_whisper.flower_supplier.common.pojo;

import java.time.LocalDateTime;

/**
 * @author tong
 * @date 2021/12/23
 */

public class OrderVo {

    public static final Integer ALL = 0;
    public static final Integer WAIT_PAY = 1;
    public static final Integer WAIT_SEND_GOOD = 2;
    public static final Integer WAIT_CONFIRM_RECEIVE = 3;
    public static final Integer FINISHED = 4;

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

    public static Integer getALL() {
        return ALL;
    }

    public static Integer getWaitPay() {
        return WAIT_PAY;
    }

    public static Integer getWaitSendGood() {
        return WAIT_SEND_GOOD;
    }

    public static Integer getWaitConfirmReceive() {
        return WAIT_CONFIRM_RECEIVE;
    }

    public static Integer getFINISHED() {
        return FINISHED;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Integer logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getOnePrice() {
        return onePrice;
    }

    public void setOnePrice(Integer onePrice) {
        this.onePrice = onePrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderStateText() {
        return orderStateText;
    }

    public void setOrderStateText(String orderStateText) {
        this.orderStateText = orderStateText;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogisticsOrderNumber() {
        return logisticsOrderNumber;
    }

    public void setLogisticsOrderNumber(String logisticsOrderNumber) {
        this.logisticsOrderNumber = logisticsOrderNumber;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", shopId=" + shopId +
                ", logisticsStatus=" + logisticsStatus +
                ", logisticsId=" + logisticsId +
                ", userId=" + userId +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", shopName='" + productName + '\'' +
                ", shopImg='" + productImg + '\'' +
                ", onePrice=" + onePrice +
                ", totalPrice=" + totalPrice +
                ", amount=" + amount +
                ", orderState=" + orderState +
                ", orderStateText='" + orderStateText + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", address='" + address + '\'' +
                ", logisticsOrderNumber='" + logisticsOrderNumber + '\'' +
                '}';
    }

    public OrderVo() {
    }

    public OrderVo(Integer orderId, Integer productId, Integer shopId, Integer logisticsStatus, Integer logisticsId, Integer userId, Integer isDeleted, LocalDateTime createTime, String shopName, String shopImg, Integer onePrice, Integer totalPrice, Integer amount, Integer orderState, String orderStateText, String receiverName, String receiverPhone, String address, String logisticsOrderNumber) {
        this.orderId = orderId;
        this.productId = productId;
        this.shopId = shopId;
        this.logisticsStatus = logisticsStatus;
        this.logisticsId = logisticsId;
        this.userId = userId;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.productName = shopName;
        this.productImg = shopImg;
        this.onePrice = onePrice;
        this.totalPrice = totalPrice;
        this.amount = amount;
        this.orderState = orderState;
        this.orderStateText = orderStateText;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.address = address;
        this.logisticsOrderNumber = logisticsOrderNumber;
    }
}
