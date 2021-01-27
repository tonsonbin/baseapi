package com.tb.app.model.sys.mapper;

import com.tb.app.common.persistence.CrudMapper;
import com.tb.app.model.sys.entity.SmsCode;

public interface SmsMapper extends CrudMapper<SmsCode> {
	
	public SmsCode getSmsCode(SmsCode code);
	
}