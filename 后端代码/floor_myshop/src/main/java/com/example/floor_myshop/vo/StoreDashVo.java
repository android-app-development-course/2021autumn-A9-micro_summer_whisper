package com.example.floor_myshop.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tong
 * @date 2021/12/23
 */
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StoreDashVo {

     private Integer todayVisit;
     private Integer yesterdayVisit;
     private Integer todayOrder;
     private Integer yesterdayOrder;
     private Integer waitConfirmGood;
     private Integer waitSendGood;
     private Integer waitPay;



}
