package com.fc.controller.bean;

import java.util.Objects;
import org.springframework.web.multipart.MultipartFile;

public class BookInfoEditVO {

  private String bookName;
  private String bookAuthor;
  private String price;
  private String bookVersion;
  private String publisher;
  private String categoryId;
  private String description;
  private MultipartFile file1;
  private MultipartFile file2;

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

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public MultipartFile getFile1() {
    return file1;
  }

  public void setFile1(MultipartFile file1) {
    this.file1 = file1;
  }

  public MultipartFile getFile2() {
    return file2;
  }

  public void setFile2(MultipartFile file2) {
    this.file2 = file2;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookInfoEditVO that = (BookInfoEditVO) o;
    return Objects.equals(bookName, that.bookName) &&
        Objects.equals(bookAuthor, that.bookAuthor) &&
        Objects.equals(price, that.price) &&
        Objects.equals(bookVersion, that.bookVersion) &&
        Objects.equals(publisher, that.publisher) &&
        Objects.equals(categoryId, that.categoryId) &&
        Objects.equals(description, that.description) &&
        Objects.equals(file1, that.file1) &&
        Objects.equals(file2, that.file2);
  }

  @Override
  public int hashCode() {

    return Objects.hash(bookName, bookAuthor, price, bookVersion, publisher, categoryId, description, file1, file2);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookInfoEditVO{");
    sb.append("bookName='").append(bookName).append('\'');
    sb.append(", bookAuthor='").append(bookAuthor).append('\'');
    sb.append(", price='").append(price).append('\'');
    sb.append(", bookVersion='").append(bookVersion).append('\'');
    sb.append(", publisher='").append(publisher).append('\'');
    sb.append(", categoryId='").append(categoryId).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", file1=").append(file1);
    sb.append(", file2=").append(file2);
    sb.append('}');
    return sb.toString();
  }
}
