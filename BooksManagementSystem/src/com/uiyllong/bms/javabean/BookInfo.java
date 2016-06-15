package com.uiyllong.bms.javabean;

import java.util.Set;

/**
 * Created by uilong on 2016/5/27.
 */
public class BookInfo {

    private String ISBN;
    private String bookName;
    private String press;           //出版社
    private String writer;
    private String content;
    private double price;
    private Integer volumeOfConpies;
    private Integer bookStock;
    private String photo;
    private Integer bookType;
    private Set bookBarCode;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getVolumeOfConpies() {
        return volumeOfConpies;
    }

    public void setVolumeOfConpies(Integer volumeOfConpies) {
        this.volumeOfConpies = volumeOfConpies;
    }

    public Integer getBookStock() {
        return bookStock;
    }

    public void setBookStock(Integer bookStock) {
        this.bookStock = bookStock;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Set getBookBarCode() {
        return bookBarCode;
    }

    public void setBookBarCode(Set bookBarCode) {
        this.bookBarCode = bookBarCode;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "ISBN='" + ISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", press='" + press + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", volumeOfConpies=" + volumeOfConpies +
                ", bookStock=" + bookStock +
                ", photo=" + photo +
                ", bookType=" + bookType +
                '}';
    }
}
