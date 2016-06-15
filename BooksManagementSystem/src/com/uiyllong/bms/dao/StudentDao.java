package com.uiyllong.bms.dao;

import com.uiyllong.bms.javabean.BorrowBooks;
import com.uiyllong.bms.javabean.Student;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by uilong on 2016/6/1.
 */
public class StudentDao extends BaseDao {

    /**
     * 根据登录的学号查询该学生当前借阅的图书
     * @param username
     * @return
     */
    public List<BorrowBooks> queryCurrentBorrowBooks(String username) {
        String hql = "from BorrowBooks where stuID = '" + username + "'";
        Query query = BaseDao.getSession().createQuery(hql);
        List<BorrowBooks> borrowBooksList = query.list();
        return borrowBooksList;
    }

    /**
     * 查询登录用户名进行验证
     * @param username
     * @return
     */
    public Student query(String username) {
        //BaseDao.getSession().beginTransaction();
        String hql = "from Student s left outer join fetch s.studentInfo where  s.studentInfo.stuID = ?";
        Query query = BaseDao.getSession().createQuery(hql);
        query.setString(0, username);
        Student student = (Student) query.uniqueResult();
        return student;
    }

}
