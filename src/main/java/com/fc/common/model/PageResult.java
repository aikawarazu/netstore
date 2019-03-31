package com.fc.common.model;

import com.google.common.primitives.Longs;
import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

  public static final long serialVersionUID = -1L;

  /**
   * 每页显示记录条数
   */
  private int pageSize = 10;
  /**
   * 总记录条数
   */
  private long totalCount;
  /**
   * 总页数
   */
  private long totalPage;
  /**
   * 当前页码
   */
  private long curPage = 1;
  /**
   * 当前页开始记录的索引
   */
  private long startIndex;

  private List<T> data;

  public PageResult(int pageSize, long totalCount) {
    if (pageSize > 0) {
      this.pageSize = pageSize;
    }
    this.totalCount = totalCount;
    calc();
  }

  public long getTotalPage() {
    return totalPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  private void calc() {
    if (totalCount == 0) {
      totalPage = 1;
    } else {
      totalPage = totalCount / pageSize;
      if (totalCount % pageSize != 0) {
        totalPage++;
      }
    }
  }

  public long getCurPage() {
    return curPage;
  }

  public void setCurPage(long curPage) {
    if (curPage <= 0) {
      return;
    }
    this.curPage = Longs.min(curPage, totalPage);
    this.startIndex = (this.curPage - 1) * pageSize;
  }

  public long getStartIndex() {
    return startIndex;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
