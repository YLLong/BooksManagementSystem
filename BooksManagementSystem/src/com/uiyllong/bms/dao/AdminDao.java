package com.uiyllong.bms.dao;

import com.uiyllong.bms.javabean.*;
import org.hibernate.Query;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by uilong on 2016/5/25.
 */
public class AdminDao extends BaseDao {

    /**
     * 借书后向借书表插入数据
     * @param books
     */
    public void saveBorrowBook(BorrowBooks books) {
        Transaction ts = BaseDao.getSession().beginTransaction();
        try {
            BorrowBooks borrowBooks = new BorrowBooks();
            borrowBooks.setStuID(books.getStuID());
            borrowBooks.setStuName(books.getStuName());
            borrowBooks.setBookID(books.getBookID());
            borrowBooks.setBookName(books.getBookName());
            borrowBooks.setCheckoutDate(books.getCheckoutDate());
            borrowBooks.setsReturnDate(books.getsReturnDate());
            BaseDao.getSession().save(borrowBooks);
            ts.commit();
        } catch (Exception e) {
            ts.commit();
        } finally {
            if (ts != null) {
                ts = null;
                BaseDao.getclose();
            }
        }
    }

    /**
     * 借书后，图书库存 -1
     * @param bookID
     */
    public void borrowBook(String bookID) {
        Transaction ts = BaseDao.getSession().beginTransaction();
        String hql = "from BookBarCode b left outer join fetch b.bookInfo where b.bookID = ?";
        try {
            Query query = BaseDao.getSession().createQuery(hql);
            query.setParameter(0, bookID);
            BookBarCode bookBarCode = (BookBarCode) query.uniqueResult();
            Integer num = bookBarCode.getBookInfo().getBookStock() - 1;
            String ISBN = bookBarCode.getBookInfo().getISBN();
            hql = "update BookInfo b set b.bookStock = " + num + " where b.ISBN = '" + ISBN+ "'";
            Query query1 = BaseDao.getSession().createQuery(hql);
            query1.executeUpdate();
            ts.commit();
        } catch (Exception e) {
            ts.commit();
        } finally {
            if (ts != null) {
                ts = null;
                BaseDao.getclose();
            }
        }
    }

    /**
     * 根据图书条形码查询图书信息
     */
    public BookBarCode queryBookInfoByID(String bookID) {
        String hql = "from BookBarCode b left outer join fetch b.bookInfo where b.bookID = ?";
        Query query = BaseDao.getSession().createQuery(hql);
        query.setParameter(0, bookID);
        BookBarCode bookBarCode = (BookBarCode) query.uniqueResult();
        BaseDao.getclose();
        return bookBarCode;
    }

    /**
     * 根据学号查找学生信息
     * @param stuID
     * @return
     */
    public StudentInfo queryStudentByStuID(String stuID) {
        //Transaction ts = BaseDao.getSession().beginTransaction();
        String hql = "from StudentInfo where stuID = ?";
        Query query = BaseDao.getSession().createQuery(hql);
        query.setString(0, stuID);
        StudentInfo studentInfo = (StudentInfo) query.uniqueResult();
        BaseDao.getclose();
        return studentInfo;
    }

    /**
     * 每页显示的图书信息
     * @param pageNum   所在页数，计算显示的是从哪个数开始，默认是0开始
     * @param pageSize  每页显示的个数
     * @return 返回一个 list 是该页显示的数据
     */
    public List<BookInfo> queryBooks(int pageNum, int pageSize, String bookName) {
        Transaction ts = BaseDao.getSession().beginTransaction();
        List<BookInfo> list = null;
        String hql = "";
        try {
            hql = "from BookInfo where bookName like '%" + bookName + "%'";
            Query query = BaseDao.getSession().createQuery(hql);
            query.setFirstResult((pageNum - 1) * pageSize);
            query.setMaxResults(pageSize);
            list = query.list();
            ts.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return list;
        } finally {
            if (ts != null)
                ts = null;
            BaseDao.getclose();
        }

    }

    /**
     * 计算查询出来数总数
     * @return
     */
    public int getBookCount(String bookName) {
        int i = 0;
        String hql = "";
        Transaction ts = BaseDao.getSession().beginTransaction();
        try {
            hql = "from BookInfo where bookName like '%" + bookName + "%'";
            Query query = BaseDao.getSession().createQuery(hql);
            i = query.list().size();
            ts.commit();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return i;
        } finally {
            if (ts != null) {
                ts = null;
                BaseDao.getclose();
            }
        }
    }

    /**
     * 查询登录用户名进行验证
     * @param username
     * @return
     */
    public Admin query(String username) {
        //BaseDao.getSession().beginTransaction();
        String hql = "from Admin a left outer join fetch a.admininfo where  a.admininfo.adminID = ?";
        Query query = BaseDao.getSession().createQuery(hql);
        query.setString(0, username);
        Admin admin = (Admin) query.uniqueResult();
        return admin;
    }

}
