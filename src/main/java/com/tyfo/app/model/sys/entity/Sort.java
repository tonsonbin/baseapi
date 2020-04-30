package com.tyfo.app.model.sys.entity;

import com.tyfo.app.common.persistence.DataEntity;

/**
 * 
 */
public class Sort extends DataEntity<Sort> {
    /**
     * 非通分类id
     */
    private String ftSortId;

    /**
     * 非通分类父id
     */
    private String ftSortBid;

    /**
     * 非通分类名称
     */
    private String ftSortName;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 云商分类id
     */
    private String tyfoSortId;

    /**
     * 云商分类父id
     */
    private String tyfoSortBid;

    /**
     * 云商分类名称
     */
    private String tyfoSortName;

    /**
     * 非通分类id
     * @return ft_sort_id 非通分类id
     */
    public String getFtSortId() {
        return ftSortId;
    }

    public void setFtSortId(String ftSortId) {
        this.ftSortId = ftSortId;
    }

    /**
     * 非通分类父id
     * @return ft_sort_bId 非通分类父id
     */
    public String getFtSortBid() {
        return ftSortBid;
    }

    public void setFtSortBid(String ftSortBid) {
        this.ftSortBid = ftSortBid;
    }

    /**
     * 非通分类名称
     * @return ft_sort_name 非通分类名称
     */
    public String getFtSortName() {
        return ftSortName;
    }

    public void setFtSortName(String ftSortName) {
        this.ftSortName = ftSortName;
    }

    /**
     * 层级
     * @return level 层级
     */
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 云商分类id
     * @return tyfo_sort_id 云商分类id
     */
    public String getTyfoSortId() {
        return tyfoSortId;
    }

    public void setTyfoSortId(String tyfoSortId) {
        this.tyfoSortId = tyfoSortId;
    }

    /**
     * 云商分类父id
     * @return tyfo_sort_bId 云商分类父id
     */
    public String getTyfoSortBid() {
        return tyfoSortBid;
    }

    public void setTyfoSortBid(String tyfoSortBid) {
        this.tyfoSortBid = tyfoSortBid;
    }

    /**
     * 云商分类名称
     * @return tyfo_sort_name 云商分类名称
     */
    public String getTyfoSortName() {
        return tyfoSortName;
    }

    public void setTyfoSortName(String tyfoSortName) {
        this.tyfoSortName = tyfoSortName;
    }

}