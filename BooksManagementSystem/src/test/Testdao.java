package test;

import com.uiyllong.bms.dao.BaseDao;
import com.uiyllong.bms.javabean.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by uilong on 2016/5/25.
 */
public class Testdao {

    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction ts = null;

    /**
     * 根据 ISBN 删除图书信息
     */
    @Test
    public void deleteBook() {
        BookInfo bookInfo = session.get(BookInfo.class, "978-8-121-25234-9");
        session.delete(bookInfo);
        ts.commit();
    }

    /**
     * 根据 ISBN 查询图书信息
     */
    @Test
    public void queryBookInfoByISBN() {
        String hql = "from BookInfo b where b.ISBN = '978-1-121-25234-9'";
        Query query = session.createQuery(hql);
        BookInfo bookInfo = (BookInfo) query.uniqueResult();
        System.out.println(bookInfo);
    }

    /**
     * 根据图书条形码查找图书
     */
    @Test
    public void queryBookInfoByID() {
        String hql = "from BookBarCode b left outer join fetch b.bookInfo where b.bookID = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, "8653891026");
        BookBarCode bookBarCode = (BookBarCode) query.uniqueResult();
        System.out.println(bookBarCode);
    }

    /**
     * 根据学生ID来查询学生信息
     */
    @Test
    public void queryStudetInfoByStuID() {
        String hql = "from StudentInfo where stuID = '201310420222'";
        Query query = BaseDao.getSession().createQuery(hql);
        StudentInfo studentInfo = (StudentInfo) query.uniqueResult();
        System.out.println(studentInfo);
    }

    /**
     * save学生信息
     */
    @Test
    public void saveStu() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR,4);
        Date date=cal.getTime();
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setStuID("201310420222");
        studentInfo.setStuName("AA");
        studentInfo.setGender("男");
        studentInfo.setAge(20);
        studentInfo.setBirth(new Date());
        studentInfo.setAddress("四川");
        studentInfo.setTele("184*****128");
        studentInfo.setStuType("本科生");
        studentInfo.setEnterDate(new Date());
        studentInfo.setGraduateDate(date);
        Student student = new Student();
        student.setStudentInfo(studentInfo);
        student.setPassword("111111");
        session.save(student);
        ts.commit();
    }

    /**
     * 图书类型及位置
     */
    @Test
    public void saveBookType() {
        BooksType b = new BooksType();
        b.setBookLaction("101");
        session.save(b);
        ts.commit();
    }

    /**
     *
     * 查询书的位置
     */
    @Test
    public void queryBooks() {
        String hql = "select a.ISBN, a.bookName, a.bookStock, b.bookLaction from bms_bookinfo a, bms_bookstype b where a.bookName like '金瓶梅' and b.bookType = a.bookType";
        SQLQuery query = session.createSQLQuery(hql);
        query.addScalar("ISBN", StringType.INSTANCE);
        query.addScalar("bookName", StringType.INSTANCE);
        query.addScalar("bookStock", IntegerType.INSTANCE);
        query.addScalar("bookLaction", StringType.INSTANCE);
        List books = query.list();
        ts.commit();
        System.out.println(books.size());
        Iterator it = books.iterator();
        while (it.hasNext()) {
            Object[] book = (Object[]) it.next();
            System.out.println("ISBN:" + book[0]);
            System.out.println("bookName:" + book[1]);
            System.out.println("bookStock:" + book[2]);
            System.out.println("bookLaction:" + book[3]);
        }
    }

    /**
     * 根据书名模糊查询
     */
    @Test
    public void query() {
        String hql = "from BookInfo b where b.bookName like '%A%'";
        Query query = session.createQuery(hql);
        List<BookInfo> bookInfos = query.list();
        ts.commit();
        System.out.println(bookInfos);
    }

    /**
     * 存储图书信息并生成图书ID(多对一单向关联)
     */
    @Test
    public void saveBooks() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setISBN("978-8-121-25234-9");
        bookInfo.setBookName("YLL");
        bookInfo.setPress("YLL出版社");
        bookInfo.setWriter("yll");
        bookInfo.setContent("这是测试删除图书");
        bookInfo.setPrice(87.07);
        bookInfo.setVolumeOfConpies(1);
        bookInfo.setBookStock(3);
        bookInfo.setBookType(3);
        for (int i = 0; i < 3; i++) {
            BookBarCode barCode = new BookBarCode();
            barCode.setBookID(getBookID(6));
            barCode.setBookInfo(bookInfo);
            session.save(barCode);
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
        ts.commit();

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



    /**
     * 测试 数据库的连接及建立表
     */
    @Test
    public void demo() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdminID("111111");
        adminInfo.setAdminName("张三");
        adminInfo.setGender("男");
        adminInfo.setAge(25);
        adminInfo.setIDcard("513902199412061876");
        adminInfo.setTele("12345678901");
        adminInfo.setBirth(new Date());
        adminInfo.setEducated("本科");
        adminInfo.setAddress("四川");
        adminInfo.setHiredate(new Date());
        Admin admin = new Admin();
        admin.setAdmininfo(adminInfo);
        admin.setPassword("123123");
        session.save(admin);
        ts.commit();
    }

    @Before
    public void conn() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        ts = session.beginTransaction();
    }

    @After
    public void  close() {
        session.close();
        sessionFactory.close();
    }

}
