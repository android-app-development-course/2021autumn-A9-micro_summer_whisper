package com.micro_summer_whisper.flower_supplier.common.pojo;

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

    public ProductVo(Integer productId, Integer categoryId, String categoryName, String productName, String productDesc, String imgAddr, Integer normalPrice, Integer promotionPrice, Integer priority, LocalDateTime createTime, LocalDateTime lastEditTime, Integer enableStatus, Integer shopId, Integer isDeleted, Integer stock, String pictureA, String pictureB, String pictureC, String pictureD, String pictureE, String pictureF) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productDesc = productDesc;
        this.imgAddr = imgAddr;
        this.normalPrice = normalPrice;
        this.promotionPrice = promotionPrice;
        this.priority = priority;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.enableStatus = enableStatus;
        this.shopId = shopId;
        this.isDeleted = isDeleted;
        this.stock = stock;
        this.pictureA = pictureA;
        this.pictureB = pictureB;
        this.pictureC = pictureC;
        this.pictureD = pictureD;
        this.pictureE = pictureE;
        this.pictureF = pictureF;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public Integer getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Integer normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Integer getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Integer promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPictureA() {
        return pictureA;
    }

    public void setPictureA(String pictureA) {
        this.pictureA = pictureA;
    }

    public String getPictureB() {
        return pictureB;
    }

    public void setPictureB(String pictureB) {
        this.pictureB = pictureB;
    }

    public String getPictureC() {
        return pictureC;
    }

    public void setPictureC(String pictureC) {
        this.pictureC = pictureC;
    }

    public String getPictureD() {
        return pictureD;
    }

    public void setPictureD(String pictureD) {
        this.pictureD = pictureD;
    }

    public String getPictureE() {
        return pictureE;
    }

    public void setPictureE(String pictureE) {
        this.pictureE = pictureE;
    }

    public String getPictureF() {
        return pictureF;
    }

    public void setPictureF(String pictureF) {
        this.pictureF = pictureF;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", imgAddr='" + imgAddr + '\'' +
                ", normalPrice=" + normalPrice +
                ", promotionPrice=" + promotionPrice +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", enableStatus=" + enableStatus +
                ", shopId=" + shopId +
                ", isDeleted=" + isDeleted +
                ", stock=" + stock +
                ", pictureA='" + pictureA + '\'' +
                ", pictureB='" + pictureB + '\'' +
                ", pictureC='" + pictureC + '\'' +
                ", pictureD='" + pictureD + '\'' +
                ", pictureE='" + pictureE + '\'' +
                ", pictureF='" + pictureF + '\'' +
                '}';
    }
}
