package com.micro_summer_whisper.flower_supplier.common.pojo;


import java.io.Serializable;

/**
 * @author tong
 * @date 2021/12/24
 */

public class ChatMessageVo implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public ChatMessageVo() {
    }

    public ChatMessageVo(String headImageLink, String content, Integer msgType, Integer hostId, Integer guestId, String nickName, Integer isText) {
        this.headImageLink = headImageLink;
        this.content = content;
        this.msgType = msgType;
        this.hostId = hostId;
        this.guestId = guestId;
        this.nickName = nickName;
        this.isText = isText;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public Integer getGuestId() {
        return guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    @Override
    public String toString() {
        return "ChatMessageVo{" +
                "headImageLink='" + headImageLink + '\'' +
                ", content='" + content + '\'' +
                ", msgType=" + msgType +
                ", hostId=" + hostId +
                ", guestId=" + guestId +
                ", nickName='" + nickName + '\'' +
                ", isText=" + isText +
                '}';
    }

    public String getHeadImageLink() {
        return headImageLink;
    }

    public void setHeadImageLink(String headImageLink) {
        this.headImageLink = headImageLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }



    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getIsText() {
        return isText;
    }

    public void setIsText(Integer isText) {
        this.isText = isText;
    }
}
