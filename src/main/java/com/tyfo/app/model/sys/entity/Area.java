package com.tyfo.app.model.sys.entity;

import com.tyfo.app.common.persistence.DataEntity;

/**
 * 
 */
public class Area extends DataEntity<Area> {
    /**
     * 非通区域id
     */
    private String ftAreaId;

    /**
     * 非通区域父id
     */
    private String ftAreaBid;

    /**
     * 非通区域名称
     */
    private String ftAreaName;

    /**
     * 层级 1.省、2.市、3.区、4.县
     */
    private Integer level;

    /**
     * 云商区域id
     */
    private String tyfoAreaId;

    /**
     * 云商区域父id
     */
    private String tyfoAreaBid;

    /**
     * 云商区域名称
     */
    private String tyfoAreaName;

    /**
     * 状态 -1=删除 1=正常
     */
    private Integer status;

    /**
     * 非通区域id
     * @return ft_area_id 非通区域id
     */
    public String getFtAreaId() {
        return ftAreaId;
    }

    public void setFtAreaId(String ftAreaId) {
        this.ftAreaId = ftAreaId;
    }

    /**
     * 非通区域父id
     * @return ft_area_bId 非通区域父id
     */
    public String getFtAreaBid() {
        return ftAreaBid;
    }

    public void setFtAreaBid(String ftAreaBid) {
        this.ftAreaBid = ftAreaBid;
    }

    /**
     * 非通区域名称
     * @return ft_area_name 非通区域名称
     */
    public String getFtAreaName() {
        return ftAreaName;
    }

    public void setFtAreaName(String ftAreaName) {
        this.ftAreaName = ftAreaName;
    }

    /**
     * 层级 1.省、2.市、3.区、4.县
     * @return level 层级 1.省、2.市、3.区、4.县
     */
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 云商区域id
     * @return tyfo_area_id 云商区域id
     */
    public String getTyfoAreaId() {
        return tyfoAreaId;
    }

    public void setTyfoAreaId(String tyfoAreaId) {
        this.tyfoAreaId = tyfoAreaId;
    }

    /**
     * 云商区域父id
     * @return tyfo_area_bId 云商区域父id
     */
    public String getTyfoAreaBid() {
        return tyfoAreaBid;
    }

    public void setTyfoAreaBid(String tyfoAreaBid) {
        this.tyfoAreaBid = tyfoAreaBid;
    }

    /**
     * 云商区域名称
     * @return tyfo_area_name 云商区域名称
     */
    public String getTyfoAreaName() {
        return tyfoAreaName;
    }

    public void setTyfoAreaName(String tyfoAreaName) {
        this.tyfoAreaName = tyfoAreaName;
    }

    /**
     * 状态 -1=删除 1=正常
     * @return status 状态 -1=删除 1=正常
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}