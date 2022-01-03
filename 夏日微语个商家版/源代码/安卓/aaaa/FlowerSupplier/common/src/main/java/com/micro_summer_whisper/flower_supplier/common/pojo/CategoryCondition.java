package com.micro_summer_whisper.flower_supplier.common.pojo;



import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */

public class CategoryCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer categoryId;


    private Boolean isExactCategoryName;

    private String categoryName;

    private String categoryDesc;

    private Integer minPriority;
    private Integer maxPriority;

    private Integer shopId;

    private Integer isDeleted;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getExactCategoryName() {
        return isExactCategoryName;
    }

    public void setExactCategoryName(Boolean exactCategoryName) {
        isExactCategoryName = exactCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
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
}
