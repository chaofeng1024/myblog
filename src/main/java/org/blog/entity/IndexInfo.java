package org.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;


public class IndexInfo {

    @TableId(value = "iid",type = IdType.AUTO)
    private Integer iid;
    @TableField(value = "info")
    private String info;

    public IndexInfo() {
    }

    public IndexInfo(Integer iid, String info) {
        this.iid = iid;
        this.info = info;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "IndexInfo{" +
                "iid=" + iid +
                ", info='" + info + '\'' +
                '}';
    }
}
