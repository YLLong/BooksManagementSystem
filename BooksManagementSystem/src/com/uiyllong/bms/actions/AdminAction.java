package com.uiyllong.bms.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.uiyllong.bms.dao.AdminDao;
import com.uiyllong.bms.javabean.Admin;
import com.uiyllong.bms.javabean.BookBarCode;
import com.uiyllong.bms.javabean.StudentInfo;
import net.sf.json.JSONObject;
import org.apache.struts2.json.annotations.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by uilong on 2016/5/25.
 */
public class AdminAction extends SuperAction implements ModelDriven<Admin> {

    private Admin admin;
    private String username;
    private String stuID;
    private StudentInfo studentInfo;
    private String bookID;
    private BookBarCode bookBarCode;
    private String result;


    /**
     * 根据图书条形码查询图书信息的 ajax 的请求
     * @return
     */
    public String queryBookByID() {
        AdminDao adminDao = new AdminDao();
        //bookID = request.getParameter("bookID");
        bookBarCode = adminDao.queryBookInfoByID(bookID);
        Map<String,Object> map = new HashMap<>();
        map.put("bookName", bookBarCode.getBookInfo().getBookName());
        JSONObject object = JSONObject.fromObject(map);
        result = object.toString();
        return "queryBookByID";
    }

    /**
     * 根据学号查询学生姓名的 ajax 请求
     * @return
     *
     */
    public String queryStudentByStuID() {
        AdminDao adminDao = new AdminDao();
        //stuID = request.getParameter("stuID");
        studentInfo = adminDao.queryStudentByStuID(stuID);
        Map<String,Object> map = new HashMap<>();
        map.put("stuName", studentInfo.getStuName());
        JSONObject object = JSONObject.fromObject(map);
        result = object.toString();
        return "queryStudentByStuID";
    }

    /**
     * 用户登录处理
     * @return
     */
    public String login() {
        AdminDao adminDao = new AdminDao();
        Admin admin1 = adminDao.query(username);
        if (admin1 != null) {
            if (admin.getPassword().equals(admin1.getPassword())) {
                admin = admin1;
                session.setAttribute("username", admin.getAdmininfo().getAdminID());
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

    /**
     * 用户注销处理
     * @return
     */
    public String logout() {
        if (session.getAttribute("username") != null) {
            session.removeAttribute("username");
        }
        return "login_error";
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    @JSON(name = "student")
    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    @JSON(name = "book")
    public BookBarCode getBookBarCode() {
        return bookBarCode;
    }

    public void setBookBarCode(BookBarCode bookBarCode) {
        this.bookBarCode = bookBarCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public Admin getModel() {
        return admin;
    }
}
