<#-- @ftlvariable name="pageResult" type="com.fc.common.model.PageResult" -->
<#-- @ftlvariable name="i" type="com.fc.controller.bean.BookInfoVO" -->
<#include "header.ftl">
  <label for="category">类型:</label>
    <select id="category" onchange="changeCategory(this.value);">
      <option value="">全部</option>
         <#list cList>
           <#items as i>
            <option value="${i.id}">${i.getName()}</option>
           </#items>
         </#list>
    </select>
<br>
<br>
<table border="0" width="438" class="center">
<#if pageResult.data??>
  <#list pageResult.data>
  <tr>
    <#items as i>
      <td>
          <#if i.file1??>
          <img width="100px" height="150px" src="${rc.contextPath}/images/${i.file1}" alt="${i.bookName}"/>
          <#else>
          <img width="100px" height="150px" src="${rc.contextPath}/images/NO_PIC.png" alt="${i.bookName}"/>
          </#if>
        <br>书名：&nbsp;&nbsp;&nbsp;&nbsp;${i.bookName}
        <br>作者：&nbsp;&nbsp;&nbsp;&nbsp;${i.bookAuthor}
        <br>出版社：&nbsp;${i.publisher}
        <br>分类：&nbsp;&nbsp;&nbsp;&nbsp;${i.categoryName}
        <br>描述：&nbsp;&nbsp;&nbsp;&nbsp;${i.description}
        <br>价格：&nbsp;&nbsp;&nbsp;&nbsp;${i.price}
        <br><a onclick="return buy(${i.bookId});" href="s">加入购物车</a>
        <br>
        <div id="book_${i.bookId}_msg" hidden="hidden">s</div>
      </td>
    </#items>
  </tr>
  </#list>
<#else>
NO items 1
</#if></table>

<div>
  第${pageResult.curPage}页/共${pageResult.totalPage }页&nbsp;&nbsp;
  <#if (pageResult.curPage > 1)>
    <#if categoryId??>
      <a href="?categoryId=${categoryId}&page=${pageResult.curPage - 1}&pageSize=${pageResult.pageSize}">上一页</a>
    <#else>
      <a href="?page=${pageResult.curPage - 1}&pageSize=${pageResult.pageSize}">上一页</a>
    </#if>
  <#else>
  <a onclick="return false;">上一页</a>
  </#if>
  <#if (pageResult.curPage < pageResult.totalPage)>
  <#--noinspection HtmlUnknownTarget-->
    <#if categoryId??>
  <a href="?categoryId=${categoryId}&page=${pageResult.curPage + 1}&pageSize=${pageResult.pageSize}">下一页</a>
    <#else>
  <a href="?page=${pageResult.curPage + 1}&pageSize=${pageResult.pageSize}">下一页</a>
    </#if>
  <#else>
  <a onclick="return false;">下一页</a>
  </#if>
</div>
<script type="text/javascript">
  <#if categoryId??>
  $('#category').val(${categoryId});
  </#if>
  function changeCategory(value) {
    window.location.replace("${rc.contextPath}/?categoryId=" + value + "&page=${pageResult.curPage}&pageSize=${pageResult.pageSize}");
  }

  function buy(id) {
    $.post({
      url: "${rc.contextPath}/cart/add",
      data: JSON.stringify({"id": id}),
      contentType: "application/json",
      success: function (data, textStatus) {
        if (data.redirect) {
          window.location.replace(data.redirect);
        } else if (data.err) {
          window.location.replace(data.err);
        } else {
          window.location.reload();
        }
      }
    });
    $('#book_' + id + '_msg').show();
    window.setTimeout(function () {
      $('#book_' + id + '_msg').hide()
    }, 2000);

    return false;
  }
</script>
<#include "footer.ftl">
