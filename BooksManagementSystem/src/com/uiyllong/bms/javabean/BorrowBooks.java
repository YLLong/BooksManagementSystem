package com.uiyllong.bms.javabean;

import java.util.Date;

/**
 * Created by uilong on 2016/5/27.
 */
public class BorrowBooks {

    private Integer id;
    private String stuID;
    private String stuName;
    private String bookID;
    private String bookName;
    private Date checkoutDate;
    private Date sReturnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getsReturnDate() {
        return sReturnDate;
    }

    public void setsReturnDate(Date sReturnDate) {
        this.sReturnDate = sReturnDate;
    }

    @Override
    public String toString() {
        return "BorrowBooks{" +
                "id=" + id +
                ", stuID='" + stuID + '\'' +
                ", stuName='" + stuName + '\'' +
                ", bookID='" + bookID + '\'' +
                ", bookName='" + bookName + '\'' +
                ", checkoutDate=" + checkoutDate +
                ", sReturnDate=" + sReturnDate +
                '}';
    }
}
