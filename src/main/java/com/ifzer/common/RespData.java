package com.ifzer.common;


import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nelson on 2018-04-19.
 */
public class RespData implements Serializable {

    private static final String OK = "0000";
    private static final String OK_MSG = "成功";
    private static final String NO_OK = "0001";
    private static final String NO_OK_MSG = "失败";

    public RespData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespData() {
        this.code = OK;
        this.msg = OK_MSG;
    }

    private String code;// 0000 表示成功，其他的为出错
    private String msg; // 消息内容
    private Object data; // 数据对象,一般是单个的时候用
    private List listData; // 数据列表对象,一般是列表或数组的时候用
    private Page pageData; // 数据分页对象,一般是分页的时候用

    public void fail(){
        this.code = NO_OK;
        this.msg = NO_OK_MSG;
    }

    public void fail(String msg){
        this.code = NO_OK;
        this.msg = msg;
    }

    public void success(){
        this.code = OK;
        this.msg = msg;
    }
    public void success(String msg){
        this.code = OK;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List getListData() {
        return listData;
    }

    public void setListData(List listData) {
        this.listData = listData;
    }

    public Page getPageData() {
        return pageData;
    }

    public void setPageData(Page pageData) {
        this.pageData = pageData;
    }
}
