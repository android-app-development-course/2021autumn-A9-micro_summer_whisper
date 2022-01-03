package com.example.floor_myshop.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * @author tong
 * @date 2021/12/24
 */
@Data
public class ChatMessageVo implements Serializable {

    public static final Integer TYPE_SEND_TEXT = 1;
    public static final Integer TYPE_SEND_PICTURE = 2;
    public static final Integer TYPE_RECEIVE_TEXT = 3;
    public static final Integer TYPE_RECEIVE_PICTURE = 4;

    private String headImageLink;
    private String content;
    private Integer msgType;
    private Integer hostId;
    private Integer guestId;
    private String nickName;
    private Integer isText;

}
