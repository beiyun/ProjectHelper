package com.beiyun.projecthelper.entity;

/**
 * Created by beiyun on 2017/11/17.
 */
public class EBT {

    private String message;

    public EBT(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
