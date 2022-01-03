package com.example.floor_myshop.model;

public enum ResponseCode {
    //这里是可以自己定义的，方便与前端交互即可
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(10000,"成功"),
    USER_NOT_EXIST(1,"用户不存在"),
    USER_IS_EXISTS(2,"用户已存在"),
    DATA_IS_NULL(3,"数据为空"),
    PRODUCT_IS_EXISTS(4,"商品已存在"),
    PRODUCT_NOT_EXISTS(4,"商品不存在"),
    ;

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
