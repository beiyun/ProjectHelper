package com.beiyun.library;

/**
 * Created by zqht on 2016/8/25 10:20
 * Email:zmm534635184@sina.com
 */
public class UserEntity {
    public String doctorNo;
    public String userName;           //用户名
    public String password;           //密码
    public String name;               //姓名
    public String idNumber;           //身份证号
    public String sex;                //性别
    public String phoneOne;           //手机号1
    public String phoneTwo;           //手机号2
    public String mailbox;            //邮箱
    public String fixedTelephone;     //固定电话
    public String medicalProperties;  //医务性质
    public String departmentName;     //科室名称
    public String major;               //专业
    public String workNumber;     //医生工号
    public String jobArea;   //单位所属区域
    public String areaName;
    public String practitionersDate;  //从业日期
    public String mtId;//团队id
    public String mtName;//团队名称
    public String companyName;
    public String goodAt;//擅长
    public String qq;                 //QQ
    public String weChatNumber;       //微信号
    public String address;            //家庭地址
    public String unitNumber;         //单位名称(单位编号)
    public String img;                //照片
    public String simId;//SIM卡id
    public String padId;//设备id

    @Override
    public String toString() {
        return "UserEntity{" +
                "address='" + address + '\'' +
                ", doctorNo='" + doctorNo + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneOne='" + phoneOne + '\'' +
                ", phoneTwo='" + phoneTwo + '\'' +
                ", mailbox='" + mailbox + '\'' +
                ", fixedTelephone='" + fixedTelephone + '\'' +
                ", medicalProperties='" + medicalProperties + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", major='" + major + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", jobArea='" + jobArea + '\'' +
                ", areaName='" + areaName + '\'' +
                ", practitionersDate='" + practitionersDate + '\'' +
                ", mtId='" + mtId + '\'' +
                ", mtName='" + mtName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", goodAt='" + goodAt + '\'' +
                ", qq='" + qq + '\'' +
                ", weChatNumber='" + weChatNumber + '\'' +
                ", unitNumber='" + unitNumber + '\'' +
                ", img='" + img + '\'' +
                ", simId='" + simId + '\'' +
                ", padId='" + padId + '\'' +
                '}';
    }
}
