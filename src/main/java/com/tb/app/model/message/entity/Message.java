package com.tb.app.model.message.entity;

import com.tb.app.common.persistence.DataEntity;

/**
 *
 */
public class Message extends DataEntity<Message> {
    //处理成功
    public static final int SUCCESS = 0;
    //处理失败
    public static final int FAIL = 1;
    /**
     * 飞通消息类型
     */
    private String messageType;

    /**
     * 飞通消息ID
     */
    private String messageId;

    /**
     * 处理状态（0=成功，1=处理成功，2=处理失败）
     */
    private Integer status;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 处理结果
     */
    private String result;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 飞通消息类型
     *
     * @return message_type 飞通消息类型
     */
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * 处理状态（0=成功，1=处理成功，2=处理失败）
     *
     * @return status 处理状态（0=成功，1=处理成功，2=处理失败）
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 消息内容
     *
     * @return message_content 消息内容
     */
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * 处理结果
     *
     * @return result 处理结果
     */
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}