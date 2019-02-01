package com.pojo.ro;

public class Response<T> {
    private String code = "200";
    private String message = "success";
    private T t;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDataResult() {
        return t;
    }

    public void setDataResult(T t) {
        this.t = t;
    }
}
