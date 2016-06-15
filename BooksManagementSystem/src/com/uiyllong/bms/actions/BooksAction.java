package com.uiyllong.bms.actions;

import com.opensymphony.xwork2.ActionContext;
import com.uiyllong.bms.dao.AdminDao;
import com.uiyllong.bms.dao.BookDao;
import com.uiyllong.bms.javabean.BookInfo;
import com.uiyllong.bms.javabean.BorrowBooks;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by uilong on 2016/5/28.
 */
public class BooksAction extends SuperAction {

    //分页查询
    private int pageNum;        //当前所在页数
    private int pageSize;       //每页显示的个数
    private int totalpage;      //总页数
    private String bookName;

    //图片上传
    private File photo;
    private String photoFileName;
    private String photoContentType;
    private String allowTypes;
    private String uploadDir;

    //数据处理类
    AdminDao adminDao = new AdminDao();
    BookDao bookDao = new BookDao();

    private BookInfo bookInfo;
    private String ISBN;
    private String result;

    //还书查询
    private String bookID;

    /**
     * 还书处理
     * @return
     */
    public String returnBook() {
        if (bookDao.returnBook((String) session.getAttribute("bookID"), (String)session.getAttribute("bookName"))) {
            addFieldError("return_success", "还书成功！");
        } else {
            addFieldError("return_error", "还书失败！");
        }
        return "returnBook_success";
    }


    /**
     * 还书查询
     * @return
     */
    public String queryBybooKID() {
        session.setAttribute("bookID", bookID);
        BorrowBooks borrowBooks = bookDao.queryBybooKID(bookID);
        session.setAttribute("book", borrowBooks);
        session.setAttribute("bookName", borrowBooks.getBookName());
        return "queryBybooKID_success";
    }

    /**
     * 图书更新
     * @return
     */
    public String updateBook() {
        if (bookDao.updateBook(bookInfo)) {
            addFieldError("add_success", "修改成功！");
        } else {
            addFieldError("add_error", "修改失败！");
        }
        return SUCCESS;
    }

    /**
     * 图书删除
     * @return
     */
    public String deleteBook() {
        if (bookDao.deleteBook(bookInfo.getISBN())) {
            addFieldError("delete_success", "删除成功！");
        } else {
            addFieldError("delete_error", "删除失败！");
        }
        return SUCCESS;
    }

    /**
     * 图书添加
     * @return
     */
    public String addBook() throws IOException {
        String addresult = filterType(allowTypes.split(","));
        if (addresult != null) {
            ActionContext.getContext().put("errortype", "你要上传的图片类型不对！");
            return SUCCESS;
        }
        String newfileName = null;
        String path = ServletActionContext.getServletContext().getRealPath(uploadDir);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        newfileName = getnewFileName(photoFileName);
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            FileInputStream fis = new FileInputStream(photo);
            bis = new BufferedInputStream(fis);
            FileOutputStream fos = new FileOutputStream(new File(dir, newfileName));
            bos = new BufferedOutputStream(fos);
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }

        System.out.println("success:" + path + "\\" + newfileName);
        bookInfo.setPhoto(path + "\\" + newfileName);
        if (bookDao.addBook(bookInfo)) {
            addFieldError("add_success", "添加成功！");
        } else {
            addFieldError("add_error", "添加失败！");
        }
        return SUCCESS;
    }

    /**
     * 判断上传的类型符合要求不
     * @param types
     * @return
     */
    public String filterType(String[] types) {
        String fileType = getPhotoContentType();
        for (String type : types) {
            if (type.equals(fileType)) {
                return null;
            }
        }
        return "input";
    }

    /**
     * 新图片的名字
     * @param photoFileName
     * @return
     */
    public String getnewFileName(String photoFileName) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatdate = format.format(new Date());
        int random = new Random().nextInt(100);
        int position = photoFileName.lastIndexOf(".");
        String extension = photoFileName.substring(position);
        String newfileName = formatdate + random + extension;
        return newfileName;
    }


    /**
     * 通过 ISBN 查询图书信息
     * @return
     */
    public String queryBookInfoByISBN() {
        BookDao bookDao = new BookDao();
        BookInfo bookInfo = bookDao.queryBookInfoByISBN(ISBN);
        Map<String, Object> book = new HashMap<>();
        book.put("bookName", bookInfo.getBookName());
        book.put("press", bookInfo.getPress());
        book.put("writer", bookInfo.getWriter());
        book.put("content", bookInfo.getContent());
        book.put("price", bookInfo.getPrice());
        book.put("volumeOfConpies", bookInfo.getVolumeOfConpies());
        book.put("bookStock", bookInfo.getBookStock());
        int position = bookInfo.getPhoto().lastIndexOf("\\");
        String photoName = bookInfo.getPhoto().substring(position);
        System.out.println(photoName);
        book.put("photo", photoName);
        book.put("bookType", bookInfo.getBookType());
        JSONObject jsonObject = JSONObject.fromObject(book);
        result = jsonObject.toString();
        return "queryBookInfoByISBN";
    }

    /**
     * 分页查询
     * @return
     */
    public String query() {
        List<BookInfo> bookInfos = adminDao.queryBooks(pageNum, pageSize, bookName);
        session.setAttribute("books", bookInfos);
        return "query_success";
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getAllowTypes() {
        return allowTypes;
    }

    public void setAllowTypes(String allowTypes) {
        this.allowTypes = allowTypes;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
