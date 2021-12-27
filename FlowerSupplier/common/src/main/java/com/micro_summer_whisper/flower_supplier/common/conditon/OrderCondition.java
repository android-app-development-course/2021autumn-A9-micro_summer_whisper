package com.micro_summer_whisper.flower_supplier.common.conditon;



import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tong
 * @date 2021/12/24
 */

public class OrderCondition implements Serializable {

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

    @Override
    public String toString() {
        return "OrderCondition{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", shopId=" + shopId +
                ", logisticsStatus=" + logisticsStatus +
                ", logisticsId=" + logisticsId +
                ", userId=" + userId +
                ", isDeleted=" + isDeleted +
                ", minCreateTime=" + minCreateTime +
                ", maxCreateTime=" + maxCreateTime +
                ", shopName='" + shopName + '\'' +
                ", shopImg='" + shopImg + '\'' +
                ", minOnePrice=" + minOnePrice +
                ", maxOnePrice=" + maxOnePrice +
                ", minTotalPrice=" + minTotalPrice +
                ", maxTotalPrice=" + maxTotalPrice +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", orderState=" + orderState +
                ", orderStateText='" + orderStateText + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", address='" + address + '\'' +
                ", logisticsOrderNumber='" + logisticsOrderNumber + '\'' +
                '}';
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

    public LocalDateTime getMinCreateTime() {
        return minCreateTime;
    }

    public void setMinCreateTime(LocalDateTime minCreateTime) {
        this.minCreateTime = minCreateTime;
    }

    public LocalDateTime getMaxCreateTime() {
        return maxCreateTime;
    }

    public void setMaxCreateTime(LocalDateTime maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public Integer getMinOnePrice() {
        return minOnePrice;
    }

    public void setMinOnePrice(Integer minOnePrice) {
        this.minOnePrice = minOnePrice;
    }

    public Integer getMaxOnePrice() {
        return maxOnePrice;
    }

    public void setMaxOnePrice(Integer maxOnePrice) {
        this.maxOnePrice = maxOnePrice;
    }

    public Integer getMinTotalPrice() {
        return minTotalPrice;
    }

    public void setMinTotalPrice(Integer minTotalPrice) {
        this.minTotalPrice = minTotalPrice;
    }

    public Integer getMaxTotalPrice() {
        return maxTotalPrice;
    }

    public void setMaxTotalPrice(Integer maxTotalPrice) {
        this.maxTotalPrice = maxTotalPrice;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
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
}
