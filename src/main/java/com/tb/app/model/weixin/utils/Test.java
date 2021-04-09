package com.tb.app.model.weixin.utils;

import com.coolsn.common.weixin.dao.WXDao;

public class Test {
	
	public static void main(String[] args) {

		WXDao wxDao = new WXDao().initSapp("wx1b08a5b0b90c4365", "xxxx");
		System.out.println(wxDao.wxspLogin("Xk3eSuZThNqJUYfPKkk42YC8kGWGmQYLnc+gXnW85Ei7aNWKMDLASzolj5PCPWpvNui85IzZsPBD258Q4ofr/v0ipQiS+LYqdgaKW1Kdf3C4OTC2nuDF+Bjf6V/xUnfGzC0LUTArRxzCzIZrP/NcXH7aEt2YAH+GB4A81/QWASkQZ7Bsr9adaxeKqjLHHVf130KTSAkQVottHAaz1VjNPXqsa9tXAEOqqLreiYd+1EID1wZYFSG2breDq+u4P35tAc8lByLDsQfrfvQXVE+TxhyV+YBU7DuS/m8i7N//Lq55glbH0XxIq9ZuY4jLd8CozNetjAwdHJSigM7vp+pZsxbo0UeVzbSeCdUHuZtg5zchwMA0vyot4xbHSgGm59+/Xrn7LqeqLohn3H7RBobDK1UPf2y5Yx48d4Sfg/EKk4qh7wlBRGhlLUgk8TZvUbgcNkpPDBXlmhp16swXazvoD==",
				"VXlWV22RFmaymbI9YCRXzg==", "0313QK00070tvL1HUM0001eP3r23QK08"));
		
	}
}
