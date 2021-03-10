package com.tb.app.model.weixin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tb.app.common.service.CrudService;
import com.tb.app.model.weixin.entity.WXBaseInfo;
import com.tb.app.model.weixin.mapper.WXBaseInfoMapper;

@Service
@Transactional(readOnly = true)
public class WXBaseInfoService extends  CrudService<WXBaseInfoMapper,WXBaseInfo> {

}
