package com.ifzer.modules.users.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

import com.github.crab2died.annotation.ExcelField;

/**
 * <p>
 * 
 * </p>
 * <p>Users</p>
 * @author nelson
 * @since 2018-04-19
 * @version 1.0
 * @copyright ifzer.com
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户名
     */
    @ExcelField(title="用户名")
    private String username;

    /**
     * 密码
     */
    @ExcelField(title="密码")
    private String password;

    /**
     * 创建日期
     */
    @ExcelField(title="创建日期")
    private Date createDate;

    /**
     * 更新日期
     */
    @ExcelField(title="更新日期")
    private Date updateDate;

    /**
     * 是否删除,1=是,0=否
     */
    @ExcelField(title="是否删除,1=是,0=否")
    private String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "Users{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", del=" + del +
        "}";
    }
}
