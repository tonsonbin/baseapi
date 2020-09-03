package com.tb.app.model.message.service;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.app.common.mapper.JsonMapper;
import com.tb.app.common.persistence.Page;
import com.tb.app.common.service.CrudService;
import com.tb.app.model.message.entity.FtMessage;
import com.tb.app.model.message.entity.Message;
import com.tb.app.model.message.mapper.MessageMapper;

/**
 * @ClassName MessageService
 * @Description [**消息删除Service**]
 * @Author Benjamin
 * @Date 2019/12/28 17:14
 * @Version 1.0
 **/
@Service
@Transactional(readOnly = true)
public class MessageService extends CrudService<MessageMapper, Message> {

    private Logger log4j = LogManager.getLogger(MessageService.class);


    public Message get(String id) {
        return super.get(id);
    }

    public List<Message> findList(Message message) {
        return super.findList(message);
    }

    public Page<Message> findPage(Page<Message> page, Message message) {
        return super.findPage(page, message);
    }

    @Transactional(readOnly = false)
    public void save(Message message) {
        super.save(message);
    }

    @Transactional(readOnly = false)
    public void delete(Message message) {
        super.delete(message);
    }


    /**
     * @param ftMessage 飞通消息本体
     * @param status    消息状态
     * @param result    处理结果
     * @param remarks   备注
     * @return int
     * @author Benjamin
     * @description [**插入消息处理日志**]
     * @date 14:27 2019/12/29
     * @version 1.0
     **/
    @Transactional(readOnly = false)
    public void addMessageLog(@NotNull FtMessage ftMessage, @NotNull int status, String result, String remarks) {
        Message message = new Message();
        message.setMessageId(ftMessage.getId());
        message.setMessageType(ftMessage.getType());
        try {
            message.setMessageContent(JsonMapper.getMapper().writeValueAsString(ftMessage));
        } catch (Exception e) {
            message.setMessageContent("消息内容转码错误");
        }
        message.setStatus(status);
        message.setResult(result);
        message.setRemarks(remarks);
        message.setCreateDate(new Date());
        save(message);
    }

    
}
