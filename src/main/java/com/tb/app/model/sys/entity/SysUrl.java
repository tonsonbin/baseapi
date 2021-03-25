package com.tb.app.model.sys.entity;

import com.tb.app.common.persistence.DataEntity;

/**
 * 
 */
public class SysUrl extends DataEntity<SysUrl> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 父级编号
     */
    private String parentId;

    /**
     * 所有父级编号
     */
    private String parentIds;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 链接
     */
    private String href;

    /**
     * 父级编号
     * @return parent_id 父级编号
     */
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 所有父级编号
     * @return parent_ids 所有父级编号
     */
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 排序
     * @return sort 排序
     */
    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 链接
     * @return href 链接
     */
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}