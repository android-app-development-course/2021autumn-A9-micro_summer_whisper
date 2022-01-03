package com.example.floor_myshop.entity;

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
public class Science implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer scienceId;

    private String category;

    private Integer isPublish;

    private String content;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;

    private Integer isDeleted;

    private Integer accountId;

    private Integer enableStatus;


    public Integer getScienceId() {
        return scienceId;
    }

    public void setScienceId(Integer scienceId) {
        this.scienceId = scienceId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Override
    public String toString() {
        return "Science{" +
        "scienceId=" + scienceId +
        ", category=" + category +
        ", isPublish=" + isPublish +
        ", content=" + content +
        ", description=" + description +
        ", createTime=" + createTime +
        ", lastEditTime=" + lastEditTime +
        ", isDeleted=" + isDeleted +
        ", accountId=" + accountId +
        ", enableStatus=" + enableStatus +
        "}";
    }
}
