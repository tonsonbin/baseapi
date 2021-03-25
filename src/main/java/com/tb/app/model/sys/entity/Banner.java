package com.tb.app.model.sys.entity;

import com.tb.app.common.persistence.DataEntity;

/**
 * 
 */
public class Banner extends DataEntity<Banner> {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String url;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}