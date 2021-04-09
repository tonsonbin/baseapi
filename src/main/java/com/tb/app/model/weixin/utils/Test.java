package com.tb.app.model.weixin.utils;

import com.coolsn.common.weixin.dao.WXDao;

public class Test {
	
	public static void main(String[] args) {

		WXDao wxDao = new WXDao().initSapp("wx1b08a5b0b90c4365", "edd5ce61f229bf13e4e4464a49c98468");
		System.out.println(wxDao.wxspLogin("GLT9Y3Xq9I0BZ09jdgoXsGzA1QMPCSPlA97VX6/93V52jbSwy66tPUnZfGhGjjToNfoCHVZuWfEX8TPrByRi+kusHCG6YmOggaweSF2vh/M6uQNW/0DbnDjaq8Y2JTH9hKGjKC1hkLKywJUbKcnqscEDEZ3IRtuu/IEiBBqTAYlDLw6ES6eR3D3RDKvQdD4BC0Iz47MuzJk5ySwzkHciSO6przwvgtygnF4OxzRHcsMp/vXPH6trKehsfe+iA4t80ifwLwjlrOSXepuqDHTe65fNYv6TKIkjUpQtlJb4J/+gb9upwHRtbY13Pmk/1RLzbQtVokp5NVGAqhrNOtW431n4XhwE29u8iObyUoSdUDFTghgdupyKLCHXHQ7W5WJkBz0X7G4OaHqZ5LjviIYPzd3mq09VFtKko3Uh8oeVcpgarxYUdy6JSDXnsfPGqVQwk5qbqLyBMebAUTTXh5Oujg==",
				"rSwq3w2t+j2y1rq9LA6uNg==", "071T2vFa1jBCPA0cRVFa1NyMCM2T2vF3"));
		
	}
}
