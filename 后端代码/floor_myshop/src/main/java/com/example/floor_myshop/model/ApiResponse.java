package com.example.floor_myshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口返回对象
 * @author 184****4909
 * @date 2021/12/20 21:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private Boolean success;

    private String message;

    private Integer code;

    private T data;

    /**
     * 请求成功
     * @author 184****4909
     * @date 2021/12/20 21:36
     */
    public static <E> ApiResponse<E> success(E data) {
        return ApiResponse.success("请求成功", data);
    }


    /**
     * 请求成功
     * @author 184****4909
     * @date 2021/12/20 21:36
     */
    public static <E> ApiResponse<E> success(String message, E data) {
        return new ApiResponse<>(Boolean.TRUE, message, ResponseCode.SUCCESS.getCode(),data);
    }

    /**
     * 请求成功
     * @author 184****4909
     * @date 2021/12/20 21:36
     */
    public static <E> ApiResponse<E> success(String message, Integer code , E data) {
        return new ApiResponse<>(Boolean.TRUE, message,code, data);
    }

    /**
     * 请求失败
     * @author 184****4909
     * @date 2021/12/20 21:36
     */
    public static ApiResponse failed() {
        return ApiResponse.failed("请求失败");
    }

    /**
     * 请求失败
     * @author 184****4909
     * @date 2021/12/20 21:36
     */
    public static ApiResponse failed(String message) {
        return new ApiResponse<>(Boolean.FALSE, message,ResponseCode.UNKNOWN_ERROR.getCode(), null);
    }

    /**
     * 请求失败
     * @author 184****4909
     * @date 2021/12/20 21:36
     */
    public static ApiResponse failed(String message, Integer code) {
        return new ApiResponse<>(Boolean.FALSE, message, code, null);
    }


}
