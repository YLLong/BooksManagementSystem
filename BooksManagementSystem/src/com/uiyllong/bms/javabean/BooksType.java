package com.uiyllong.bms.javabean;

import java.io.Serializable;

/**
 * Created by uilong on 2016/5/27.
 */
public class BooksType implements Serializable {

    private Integer bookType;
    private String bookLaction;

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public String getBookLaction() {
        return bookLaction;
    }

    public void setBookLaction(String bookLaction) {
        this.bookLaction = bookLaction;
    }

    @Override
    public String toString() {
        return "BooksType{" +
                "bookType='" + bookType + '\'' +
                ", bookLaction='" + bookLaction + '\'' +
                '}';
    }
}
