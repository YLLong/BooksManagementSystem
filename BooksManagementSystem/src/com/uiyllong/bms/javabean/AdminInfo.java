package com.uiyllong.bms.javabean;

import java.util.Date;

/**
 * Created by uilong on 2016/5/26.
 */
public class AdminInfo {

    private String adminID;
    private String adminName;
    private String gender;
    private Integer age;
    private String IDcard;      //身份证
    private String tele;        //电话号码
    private Date birth;
    private String educated;    //学历
    private String address;     //地址
    private Date hiredate;    //入职时间
    private Admin admin;

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEducated() {
        return educated;
    }

    public void setEducated(String educated) {
        this.educated = educated;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "adminID='" + adminID + '\'' +
                ", adminName='" + adminName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", IDcard='" + IDcard + '\'' +
                ", tele='" + tele + '\'' +
                ", birth=" + birth +
                ", educated='" + educated + '\'' +
                ", address='" + address + '\'' +
                ", hiredate=" + hiredate +
                '}';
    }
}
