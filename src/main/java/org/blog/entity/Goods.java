package org.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("item_id")
    private String type;

    @TableField("goods_id")
    private Integer goodId;

    @TableField("title")
    private String title;

    @TableField("small_pic")
    private String imageSrc;

    @TableField("price")
    private BigDecimal price;  //价格

    @TableField("status")
    private String status;  // 状态

    @TableField("stock_num")
    private Integer stock;  //库存

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    public Goods() {
    }

    public Goods(Integer id, String type, Integer goodId, String title, String imageSrc, BigDecimal price, String status, Integer stock, Date createTime, Date updateTime) {
        this.id = id;
        this.type = type;
        this.goodId = goodId;
        this.title = title;
        this.imageSrc = imageSrc;
        this.price = price;
        this.status = status;
        this.stock = stock;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", goodId=" + goodId +
                ", title='" + title + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", stock=" + stock +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
