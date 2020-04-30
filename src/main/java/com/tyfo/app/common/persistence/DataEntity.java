package com.tyfo.app.common.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tyfo.app.common.YamlConfig;
import com.tyfo.app.common.security.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @Description 实体类基类
 * @Author Benjamin
 * @CreateDate 2018-12-19 10:20
 **/
public class DataEntity<T> extends BaseEntity<T> {
    @JsonBackReference(value = "remarks")
    protected String remarks;    // 备注

    @JsonBackReference(value = "createDate")
    protected Date createDate;    // 创建日期

    @JsonBackReference(value = "updateDate")
    protected Date updateDate = new Date();    // 更新日期
    @JsonBackReference(value = "delFlag")
    protected String delFlag;    // 删除标记（0：正常；1：删除）


    public DataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public DataEntity(String id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (!this.isNewRecord) {
            //setId(IdGen.uuid());
        }

        this.updateDate = new Date();
        if (this.createDate == null) {
            this.createDate = this.updateDate;
        }
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        this.updateDate = new Date();
    }

    @Length(min = 0, max = 255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Length(min = 1, max = 1)
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
