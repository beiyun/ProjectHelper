package com.beiyun.library.entity;

import java.util.List;

/**
 * Created by beiyun on 2017/12/29.
 */
public class DialogEntity {

    //title
    private String title;
    //simple content
    private String message;
    //simple string list
    private List<String> simpleList;
    //simple string array
    private String[] simpleArray;
    //checkBox string array
    private String[] checkBoxArray;
    //checkBox string list
    private List<String> checkBoxList;
    //positive button text
    private String positiveText;
    //negative button text
    private String negativeText;
    //neutral button text
    private String neutralText;

    public String[] getCheckBoxArray() {
        return checkBoxArray;
    }

    public void setCheckBoxArray(String[] checkBoxArray) {
        this.checkBoxArray = checkBoxArray;
    }

    public List<String> getCheckBoxList() {
        return checkBoxList;
    }

    public void setCheckBoxList(List<String> checkBoxList) {
        this.checkBoxList = checkBoxList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public String getNeutralText() {
        return neutralText;
    }

    public void setNeutralText(String neutralText) {
        this.neutralText = neutralText;
    }

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String[] getSimpleArray() {
        return simpleArray;
    }

    public void setSimpleArray(String[] simpleArray) {
        this.simpleArray = simpleArray;
    }

    public List<String> getSimpleList() {
        return simpleList;
    }

    public void setSimpleList(List<String> simpleList) {
        this.simpleList = simpleList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
