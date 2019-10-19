package com.huawei.store.util;

import java.util.List;

public class JsonResult<T>  {
    protected String message;
    protected Integer state;
    protected T data;

    public JsonResult() {
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(String message, Integer state) {
        this.message = message;
        this.state = state;
    }

    public JsonResult(String message, Integer state, T data) {
        this.message = message;
        this.state = state;
        this.data = data;


    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
