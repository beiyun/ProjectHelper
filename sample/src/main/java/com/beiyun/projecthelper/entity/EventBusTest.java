package com.beiyun.projecthelper.entity;

/**
 * Created by beiyun on 2017/11/17.
 */
public class EventBusTest {

    private String message;

    public EventBusTest(String message) {
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
