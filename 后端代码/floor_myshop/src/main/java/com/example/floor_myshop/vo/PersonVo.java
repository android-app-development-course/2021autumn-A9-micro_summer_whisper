package com.example.floor_myshop.vo;

import com.example.floor_myshop.entity.Account;
import com.example.floor_myshop.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tong
 * @date 2021/12/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private String password;

    private Integer isDeleted;


    private String code;

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

    private String token;


    public Account toAccount(){
        return new Account(userId,userId,userName,password,createTime,lastEditTime,isDeleted);
    }

    public Person toPerson(){
        return new Person(userId,name,birthday,gender,phone,email,profileImg,customerFlag,shopOwnerFlag,adminFlag,createTime,
                lastEditTime,enableStatus);
    }

    public static PersonVo toPersonVo(Account one, Person p, String token){
        return new PersonVo(one.getUserId(),
                one.getUserName(),"",one.getIsDeleted(),"",p.getName(),p.getBirthday(),
                p.getGender(),p.getPhone(),p.getEmail(),p.getProfileImg(),p.getCustomerFlag(),p.getShopOwnerFlag(),
                p.getAdminFlag(),p.getCreateTime(),p.getLastEditTime(),p.getEnableStatus(),token
        );
    }

}
