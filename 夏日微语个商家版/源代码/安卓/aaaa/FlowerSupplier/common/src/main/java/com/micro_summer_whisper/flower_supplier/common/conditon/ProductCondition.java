package com.micro_summer_whisper.flower_supplier.common.conditon;


import java.time.LocalDateTime;

/**
 * @author tong
 * @date 2021/12/23
 */

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

    public Integer getMinNormalPrice() {
        return minNormalPrice;
    }

    public void setMinNormalPrice(Integer minNormalPrice) {
        this.minNormalPrice = minNormalPrice;
    }

    public Integer getMaxNormalPrice() {
        return maxNormalPrice;
    }

    public void setMaxNormalPrice(Integer maxNormalPrice) {
        this.maxNormalPrice = maxNormalPrice;
    }

    public Integer getMinPromotionPrice() {
        return minPromotionPrice;
    }

    public void setMinPromotionPrice(Integer minPromotionPrice) {
        this.minPromotionPrice = minPromotionPrice;
    }

    public Integer getMaxPromotionPrice() {
        return maxPromotionPrice;
    }

    public void setMaxPromotionPrice(Integer maxPromotionPrice) {
        this.maxPromotionPrice = maxPromotionPrice;
    }

    public Integer getMinPriority() {
        return minPriority;
    }

    public void setMinPriority(Integer minPriority) {
        this.minPriority = minPriority;
    }

    public Integer getMaxPriority() {
        return maxPriority;
    }

    public void setMaxPriority(Integer maxPriority) {
        this.maxPriority = maxPriority;
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

    public LocalDateTime getMinLastEditTime() {
        return minLastEditTime;
    }

    public void setMinLastEditTime(LocalDateTime minLastEditTime) {
        this.minLastEditTime = minLastEditTime;
    }

    public LocalDateTime getMaxLastEditTime() {
        return maxLastEditTime;
    }

    public void setMaxLastEditTime(LocalDateTime maxLastEditTime) {
        this.maxLastEditTime = maxLastEditTime;
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

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }
}
