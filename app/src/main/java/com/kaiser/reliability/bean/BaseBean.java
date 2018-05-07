package com.kaiser.reliability.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ex-huangkeze001 on 2018/5/6.
 */

public class BaseBean<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    public boolean isOk(){
        return code.equals("0000");
    }
}
