package com.tyfo.app.model.message.entity;

/**
 * @ClassName FtMessage
 * @Description [**飞通消息基础类**]
 * @Author Benjamin
 * @Date 2019/12/29 10:38
 * @Version 1.0
 **/
public class FtMessage<T> {
    //消息ID
    private String id;
    //消息类型
    private String type;
    //消息时间
    private String time;
    //消息内容
    private T data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
