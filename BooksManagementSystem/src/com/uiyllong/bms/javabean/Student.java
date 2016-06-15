package com.uiyllong.bms.javabean;

/**
 * Created by uilong on 2016/5/27.
 */
public class Student {

    private Integer id;
    private StudentInfo studentInfo;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentInfo=" + studentInfo +
                ", password='" + password + '\'' +
                '}';
    }
}
