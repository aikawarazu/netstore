package com.fc.controller.bean;

import com.fc.model.BookInfo;
import java.io.Serializable;
import java.util.Objects;

public class BookInfoVO implements Serializable {

  public static final long serialVersionUID = -1L;
  private String bookId;
  private String bookName;
  private String bookAuthor;
  private String price;
  private String bookVersion;
  private String publisher;
  private String categoryName;
  private String categoryId;
  private String description;
  private String file1;
  private String file2;

  public BookInfoVO(BookInfo bookInfo, String categoryName, String price) {
    this.bookName = bookInfo.getBookName();
    this.bookId = Objects.toString(bookInfo.getBookId());
    this.bookAuthor = bookInfo.getBookAuthor();
    this.categoryId = Objects.toString(bookInfo.getCategoryId());
    this.categoryName = categoryName;
    this.price = price;
    this.bookVersion = bookInfo.getBookVersion();
    this.publisher = bookInfo.getPublisherName();
    this.description = bookInfo.getDescription();
    this.file1 = bookInfo.getPic();
    this.file2 = bookInfo.getPicture2();
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getBookVersion() {
    return bookVersion;
  }

  public void setBookVersion(String bookVersion) {
    this.bookVersion = bookVersion;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFile1() {
    return file1;
  }

  public void setFile1(String file1) {
    this.file1 = file1;
  }

  public String getFile2() {
    return file2;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public void setFile2(String file2) {
    this.file2 = file2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookInfoVO that = (BookInfoVO) o;
    return Objects.equals(bookId, that.bookId) &&
        Objects.equals(bookName, that.bookName) &&
        Objects.equals(bookAuthor, that.bookAuthor) &&
        Objects.equals(price, that.price) &&
        Objects.equals(bookVersion, that.bookVersion) &&
        Objects.equals(publisher, that.publisher) &&
        Objects.equals(categoryName, that.categoryName) &&
        Objects.equals(categoryId, that.categoryId) &&
        Objects.equals(description, that.description) &&
        Objects.equals(file1, that.file1) &&
        Objects.equals(file2, that.file2);
  }

  @Override
  public int hashCode() {

    return Objects.hash(bookId, bookName, bookAuthor, price, bookVersion, publisher, categoryName, categoryId, description, file1, file2);
  }
}
