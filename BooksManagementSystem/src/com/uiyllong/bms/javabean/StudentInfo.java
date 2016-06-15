package com.uiyllong.bms.javabean;

import java.util.Date;

/**
 * Created by uilong on 2016/5/27.
 */
public class StudentInfo {

    private String stuID;           //学号
    private String stuName;
    private String gender;
    private Integer age;
    private Date birth;
    private String address;
    private String tele;
    private String stuType;         //学历（本科, 研究生）
    private Date enterDate;         //入校时间
    private Date graduateDate;      //毕业时间
    private Student student;

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "stuID='" + stuID + '\'' +
                ", stuName='" + stuName + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", birth=" + birth +
                ", address='" + address + '\'' +
                ", tele='" + tele + '\'' +
                ", stuType='" + stuType + '\'' +
                ", enterDate=" + enterDate +
                ", graduateDate=" + graduateDate +
                '}';
    }
}
