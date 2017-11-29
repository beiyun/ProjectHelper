package com.beiyun.projecthelper.entity;

/**
 * Created by beiyun on 2017/11/28.
 */
public class EBT2 {

    private String message;

    public EBT2(String message) {
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
