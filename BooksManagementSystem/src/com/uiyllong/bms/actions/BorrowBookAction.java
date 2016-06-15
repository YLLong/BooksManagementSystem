package com.uiyllong.bms.actions;

import com.uiyllong.bms.dao.AdminDao;
import com.uiyllong.bms.javabean.BorrowBooks;

/**
 * Created by uilong on 2016/5/31.
 */
public class BorrowBookAction extends SuperAction {

    private BorrowBooks borrowBooks;

    /**
     * 借书
     * @return
     */
    public String borrowBook() {
        System.out.println(borrowBooks);
        AdminDao adminDao = new AdminDao();
        adminDao.borrowBook(borrowBooks.getBookID());
        adminDao.saveBorrowBook(borrowBooks);
        session.setAttribute("borrow_success", "借书成功！");
        return "borrowBook_success";
    }

    public BorrowBooks getBorrowBooks() {
        return borrowBooks;
    }

    public void setBorrowBooks(BorrowBooks borrowBooks) {
        this.borrowBooks = borrowBooks;
    }
}
