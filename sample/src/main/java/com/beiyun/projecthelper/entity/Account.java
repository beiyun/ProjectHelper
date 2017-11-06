package com.beiyun.projecthelper.entity;

import java.util.Set;

/**
 * Created by beiyun on 2017/11/3.
 */
public class Account {

    private String name;

    private String password;

    private int age;

    private long aLong;

    private float aFloat;

    private Set<String> stringSet;

    private boolean isLogin;

    private String phone;

    private String identify;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public float getaFloat() {
        return aFloat;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aFloat=" + aFloat +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", aLong=" + aLong +
                ", stringSet=" + stringSet +
                ", isLogin=" + isLogin +
                ", phone='" + phone + '\'' +
                ", identify='" + identify + '\'' +
                '}';
    }
}
