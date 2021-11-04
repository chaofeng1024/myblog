package org.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Lenovo
 * Date: 2020/7/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId(value = "u_id", type= IdType.AUTO)
    private Integer uId;
    @TableField("u_name")
    private String name;
    @TableField("u_password")
    private String password;
    @TableField("u_email")
    private String email;
    @TableField("u_sex")
    private String sex;
    @TableField("u_status")
    private Integer status;
    @TableField("u_phone")
    private String phone;
    @TableField("u_register_date")
    private Date registerDate;
    @TableField("u_balance")
    private BigDecimal balance;
    @TableField("u_avatar")
    private String avatar;
}
