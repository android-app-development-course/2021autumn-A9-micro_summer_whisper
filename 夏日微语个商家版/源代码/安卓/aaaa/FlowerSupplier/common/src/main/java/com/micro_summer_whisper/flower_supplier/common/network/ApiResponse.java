package com.micro_summer_whisper.flower_supplier.common.network;


/**
 * 接口返回对象
 * @author 184****4909
 * @date 2021/12/20 21:36
 */

public class ApiResponse<T> {

    private Boolean success;

    private String message;

    private Integer code;

    private T data;

    public ApiResponse(Boolean success, String message, Integer code, T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }


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

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
