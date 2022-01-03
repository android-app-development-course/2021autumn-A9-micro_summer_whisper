package com.example.floor_myshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String name;

    private LocalDateTime birthday;

    private String gender;

    private String phone;

    private String email;

    private String profileImg;

    private Integer customerFlag;

    private Integer shopOwnerFlag;

    private Integer adminFlag;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;

    private Integer enableStatus;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public Integer getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(Integer customerFlag) {
        this.customerFlag = customerFlag;
    }

    public Integer getShopOwnerFlag() {
        return shopOwnerFlag;
    }

    public void setShopOwnerFlag(Integer shopOwnerFlag) {
        this.shopOwnerFlag = shopOwnerFlag;
    }

    public Integer getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Integer adminFlag) {
        this.adminFlag = adminFlag;
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

    @Override
    public String toString() {
        return "Person{" +
        "userId=" + userId +
        ", name=" + name +
        ", birthday=" + birthday +
        ", gender=" + gender +
        ", phone=" + phone +
        ", email=" + email +
        ", profileImg=" + profileImg +
        ", customerFlag=" + customerFlag +
        ", shopOwnerFlag=" + shopOwnerFlag +
        ", adminFlag=" + adminFlag +
        ", createTime=" + createTime +
        ", lastEditTime=" + lastEditTime +
        ", enableStatus=" + enableStatus +
        "}";
    }
}
