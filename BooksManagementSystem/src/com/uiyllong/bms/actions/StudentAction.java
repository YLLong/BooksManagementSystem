package com.uiyllong.bms.actions;

import com.uiyllong.bms.dao.StudentDao;
import com.uiyllong.bms.javabean.BorrowBooks;
import com.uiyllong.bms.javabean.Student;

import java.util.List;

/**
 * Created by uilong on 2016/6/1.
 */
public class StudentAction extends SuperAction {

    private Student student;
    private String stuID;


    /**
     * 当前借阅的处理机制
     * @return
     */
    public String currentBorrow() {
        StudentDao studentDao = new StudentDao();
        List<BorrowBooks> borrowBooks = (List<BorrowBooks>) studentDao.queryCurrentBorrowBooks((String) session.getAttribute("stuID"));
        session.setAttribute("borrowInfo", borrowBooks);
        return "currentBorrow_success";
    }

    /**
     * 用户登录处理
     * @return
     */
    public String login() {
        StudentDao studentDao = new StudentDao();
        Student student1 = studentDao.query(stuID);
        System.out.println(student1);
        if (student1 != null) {
            if (student.getPassword().equals(student1.getPassword())) {
                student = student1;
                session.setAttribute("stuID", student.getStudentInfo().getStuID());
                return "login_success";
            } else {
                addFieldError("passwordERROR", "密码错误!");
                return "login_error";
            }
        } else {
            addFieldError("adminERROR", "用户名不存在!");
            return "login_error";
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }
}
