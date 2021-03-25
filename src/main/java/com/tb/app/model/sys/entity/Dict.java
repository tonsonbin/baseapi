package com.tb.app.model.sys.entity;

import com.tb.app.common.persistence.DataEntity;

import java.util.Date;

/**
 * 
 */
public class Dict extends DataEntity<Dict> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 数据值
     */
    private String value;

    /**
     * 标签名
     */
    private String label;

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序（升序）
     */
    private Long sort;

    /**
     * 父级编号
     */
    private String parent_id;

    /**
     * 创建者
     */
    private String create_by;

    /**
     * 创建时间
     */
    private Date create_date;

    /**
     * 更新者
     */
    private String update_by;

    /**
     * 更新时间
     */
    private Date update_date;

    /**
     * 删除标记
     */
    private String del_flag;

    /**
     * 数据值
     * @return value 数据值
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 标签名
     * @return label 标签名
     */
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 类型
     * @return type 类型
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 排序（升序）
     * @return sort 排序（升序）
     */
    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 父级编号
     * @return parent_id 父级编号
     */
    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    /**
     * 创建者
     * @return create_by 创建者
     */
    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * 更新者
     * @return update_by 更新者
     */
    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    /**
     * 更新时间
     * @return update_date 更新时间
     */
    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    /**
     * 删除标记
     * @return del_flag 删除标记
     */
    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }
}