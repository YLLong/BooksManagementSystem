package com.uiyllong.bms.dao;

import com.uiyllong.bms.javabean.BookBarCode;
import com.uiyllong.bms.javabean.BookInfo;
import com.uiyllong.bms.javabean.BorrowBooks;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Random;

/**
 * Created by uilong on 2016/6/2.
 */
public class BookDao extends BaseDao {

    AdminDao adminDao = new AdminDao();

    /**
     * 还书处理
     * @param bookID
     * @return
     */
    public boolean returnBook(String bookID, String bookName) {
        Session session = BaseDao.getSession();
        Transaction ts = session.beginTransaction();
        try {
            String hql = "from BorrowBooks where bookID = '" + bookID + "'";
            Query query = session.createQuery(hql);
            BorrowBooks borrowBooks = (BorrowBooks) query.uniqueResult();
            session.delete(borrowBooks);
            hql = "from BookInfo where bookName = '" + bookName + "'";
            Query query1 =session.createQuery(hql);
            BookInfo bookInfo = (BookInfo) query1.uniqueResult();
            int num = bookInfo.getBookStock();
            bookInfo.setBookStock(num + 1);
            session.update(bookInfo);
            ts.commit();
            return true;
        } catch (Exception e) {
            ts.commit();
            return false;
        } finally {
            if (ts != null) {
                ts = null;
                BaseDao.getclose();
            }
        }
    }

    /**
     * 还书中输入图书条形码查询借书情况
     * @param bookID
     * @return
     */
    public BorrowBooks queryBybooKID(String bookID) {
        Session session = BaseDao.getSession();
        Transaction ts = session.beginTransaction();
        String hql = "from BorrowBooks where bookID = '" + bookID + "'";
        try {
            Query query = session.createQuery(hql);
            BorrowBooks borrowBooks = (BorrowBooks) query.uniqueResult();
            ts.commit();
            return borrowBooks;
        } catch (Exception e) {
            ts.commit();
            return null;
        } finally {
            if (ts != null) {
                ts = null;
                BaseDao.getclose();
            }
        }
    }


    /**
     * 根据输入的 ISBN 修改图书信息
     * @param book
     * @return
     */
    public boolean updateBook(BookInfo book) {
        Session session = BaseDao.getSession();
        Transaction ts = session.beginTransaction();
        try {
            BookInfo bookInfo = queryBookInfoByISBN(book.getISBN());
            Integer num = bookInfo.getBookStock();
            bookInfo.setISBN(book.getISBN());
            bookInfo.setBookName(book.getBookName());
            bookInfo.setPress(book.getPress());
            bookInfo.setWriter(book.getWriter());
            bookInfo.setContent(book.getContent());
            bookInfo.setPrice(book.getPrice());
            bookInfo.setVolumeOfConpies(book.getVolumeOfConpies());
            bookInfo.setBookStock(book.getBookStock());
            bookInfo.setPhoto(book.getPhoto());
            bookInfo.setBookType(book.getBookType());
            for (int i = 0; i < book.getBookStock() - num; i++) {
                BookBarCode barCode = new BookBarCode();
                barCode.setBookID(getBookID(6));
                barCode.setBookInfo(bookInfo);
                session.saveOrUpdate(barCode);
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            ts.commit();
            return true;
        } catch (Exception e) {
            ts.commit();
            return false;
        } finally {
            if (ts != null) {
                ts = null;
                BaseDao.getclose();
            }
        }
    }

    /**
     * 图书出库（根据 ISBN 来查询）
     * 图书删除时要用单向的一对多 在一的一边用 set
     * @param ISBN
     * @return
     */
    public boolean deleteBook(String ISBN) {
        Session session = BaseDao.getSession();
        Transaction ts = session.beginTransaction();
        try {
            //BaseDao.getclose();
            String hql = "delete BookBarCode b where b.bookInfo.ISBN = '" + ISBN + "'";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            BookInfo bookInfo = session.get(BookInfo.class, ISBN);
            session.delete(bookInfo);
            ts.commit();
            return true;
        } catch (Exception e) {
            ts.commit();
            return false;
        } finally {
            if (ts != null) {
                ts = null;
                BaseDao.getclose();
            }
        }
    }

    /**
     * 图书添加
     * @param book
     */
    public boolean addBook(BookInfo book) {
        Session session = BaseDao.getSession();
        Transaction ts = session.beginTransaction();
        try {
            //BookInfo bookInfo = new BookInfo();
            //bookInfo.setISBN(book.getISBN());
            //bookInfo.setBookName(book.getBookName());
            //bookInfo.setPress(book.getPress());
            //bookInfo.setWriter(book.getWriter());
            //bookInfo.setContent(book.getContent());
            //bookInfo.setPrice(book.getPrice());
            //bookInfo.setVolumeOfConpies(book.getVolumeOfConpies());
            //bookInfo.setBookStock(book.getBookStock());
            //bookInfo.setPhoto(book.getPhoto());
            //bookInfo.setBookType(book.getBookType());
            for (int i = 0; i < book.getBookStock(); i++) {
                BookBarCode barCode = new BookBarCode();
                barCode.setBookID(getBookID(6));
                barCode.setBookInfo(book);
                session.save(barCode);
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            ts.commit();
            return true;
        } catch (Exception e) {
            ts.commit();
            return false;
        } finally {
            if (ts != null) {
                ts = null;
                BaseDao.getclose();
            }
        }
    }

    /**
     * 根据 ISBN 查询图书信息
     * @param ISBN
     * @return
     */
    public BookInfo queryBookInfoByISBN(String ISBN) {
        String hql = "from BookInfo b where b.ISBN = '" + ISBN + "'";
        Query query = BaseDao.getSession().createQuery(hql);
        BookInfo bookInfo = (BookInfo) query.uniqueResult();
        BaseDao.getclose();
        return bookInfo;
    }

    /**
     *
     * 生成书ID
     * @param len
     * @return
     */
    private String getBookID(int len) {
        int[] param = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        String str= String.valueOf(System.currentTimeMillis());
        str = str.substring(str.length()-4,str.length());
        String bookID = str + String.valueOf(result);
        return bookID;
    }

}
