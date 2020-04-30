package com.tyfo.app.model.message.mapper;

import com.tyfo.app.common.persistence.CrudMapper;
import com.tyfo.app.model.message.entity.Message;

import java.util.List;

public interface MessageMapper extends CrudMapper<Message> {
    //批量插入数据
    int batchInsert(List<Message> messageList);
}