package com.tb.app.model.weixin.utils.weixin;

import com.coolsn.common.weixin.dao.WXDao;

public class Test {

	public static void main(String[] args) {
		WXDao wxDao = new WXDao().init("", "");
		wxDao.getSignature("");
	}
}
