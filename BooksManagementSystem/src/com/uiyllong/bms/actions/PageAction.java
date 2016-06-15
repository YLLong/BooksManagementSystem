package com.uiyllong.bms.actions;

import com.uiyllong.bms.dao.AdminDao;

/**
 * 计算当前所在页数，总页数，每页显示的学生个数
 * Created by uilong on 2016/5/17.
 */
public class PageAction extends SuperAction{

    private int pageNum;        //当前所在页数
    private int pageSize;       //每页显示的个数
    private int totalpage;      //总页数
    private String bookName;

    @Override
    public String execute() throws Exception {
        AdminDao adminDao = new AdminDao();
        if (bookName != null) {
            session.setAttribute("bookName", bookName);
        }else {
            bookName = (String) session.getAttribute("bookName");
        }
        pageSize = 5;
        int boosCount = adminDao.getBookCount(bookName);
        totalpage = (boosCount % pageSize == 0) ? (boosCount / pageSize) : (boosCount / pageSize + 1);
        if (pageNum <= 0)
            pageNum = 1;
        if (pageNum > totalpage)
            pageNum = totalpage;
        return SUCCESS;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
