package com.mickey.tech.orm.entity;

import java.util.Date;
import javax.persistence.*;

public class User {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 行数据版本
     */
    private Long version;

    /**
     * 时间戳
     */
    private Date ts;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取用户地址
     *
     * @return address - 用户地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置用户地址
     *
     * @param address 用户地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取行数据版本
     *
     * @return version - 行数据版本
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置行数据版本
     *
     * @param version 行数据版本
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 获取时间戳
     *
     * @return ts - 时间戳
     */
    public Date getTs() {
        return ts;
    }

    /**
     * 设置时间戳
     *
     * @param ts 时间戳
     */
    public void setTs(Date ts) {
        this.ts = ts;
    }
}