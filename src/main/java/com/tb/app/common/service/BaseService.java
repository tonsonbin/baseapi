package com.tb.app.common.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Description BaseService基础类
 * @Author Benjamin
 * @CreateDate 2018-12-19 10:02
 *
 **/
@Transactional(readOnly = true)
public abstract class BaseService {
}
