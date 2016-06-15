package com.uiyllong.bms.javabean;

/**
 * Created by uilong on 2016/5/27.
 */
public class BookBarCode {

    private String bookID;
    private BookInfo bookInfo;

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    @Override
    public String toString() {
        return "BookBarCode{" +
                "bookID='" + bookID + '\'' +
                ", bookInfo=" + bookInfo +
                '}';
    }
}
