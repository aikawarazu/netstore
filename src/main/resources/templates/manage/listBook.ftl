<#-- @ftlvariable name="pageResult" type="com.fc.common.model.PageResult" -->
<#include "header.ftl">
  <table border="1" width="438" class="center">
    <tr>
      <th>序号</th>
      <th>书名</th>
      <th>封面</th>
      <th>作者</th>
      <th>出版社</th>
      <th>书籍描述</th>
      <th><label for="category">类型:</label><select id="category" onchange="changeCategory(this.value);">
        <option value="">全部</option>
         <#list cList>
           <#items as i>
            <option value="${i.id}">${i.getName()}</option>
           </#items>
         </#list>
      </select></th>
      <th>价格</th>
      <th>操作</th>
    </tr>

<#if pageResult.data??>
  <#list pageResult.data>
    <#items as i>
    <tr class="${i?item_parity}">
      <td>${i?index + 1}</td>
      <td>${i.bookName}</td>
      <td>
        <#if i.file1??>
          <img width="100px" height="150px" src="${rc.contextPath}/images/${i.file1}" alt="${i.bookName}"/>
        <#else>
        <img width="100px" height="150px" src="${rc.contextPath}/images/NO_PIC.png" alt="${i.bookName}"/>
        </#if>
      </td>
      <td>${i.bookAuthor}</td>
      <td>${i.publisher}</td>
      <td>${i.description}</td>
      <td>${i.categoryName}</td>
      <td>${i.price}</td>
      <td>
      <#--<a onclick="return false;" href="/manage/book/edit/${i.bookId}">编辑</a>-->
        <a onclick="return delById(${i.bookId});" href="#">删除</a>
      </td>
    </tr>
    </#items>
  </#list>
<#else>
NO items 1
</#if></table>

<div>
  第${pageResult.curPage}页/共${pageResult.totalPage }页&nbsp;&nbsp;
  <#if (pageResult.curPage > 1)>
  <a href="list?categoryId=${categoryId}&page=${pageResult.curPage - 1}&pageSize=${pageResult.pageSize}">上一页</a>
  <#else>
  <a onclick="return false;">上一页</a>
  </#if>
  <#if (pageResult.curPage < pageResult.totalPage)>
  <#--noinspection HtmlUnknownTarget-->
  <a href="list?categoryId=${categoryId}&page=${pageResult.curPage + 1}&pageSize=${pageResult.pageSize}">下一页</a>
  <#else>
  <a onclick="return false;">下一页</a>
  </#if>
</div>
<script type="text/javascript">
  <#if categoryId??>
  $('#category').val(${categoryId});
  </#if>
  function changeCategory(value) {
    <#--console.log("${rc.contextPath}/manage/book/list?categoryId="+value+"&page=${pageResult.page}&pageSize=${pageResult.pageSize}");-->
    window.location.replace("${rc.contextPath}/manage/book/list?categoryId="+value+"&page=${pageResult.curPage}&pageSize=${pageResult.pageSize}");
  }

  function delById(id) {
    $.post({
      url: "${rc.contextPath}/manage/book/del",
      data: JSON.stringify({"id": id}),
      contentType: "application/json",
      success: function (data, textStatus) {
        if (data.redirect) {
          window.location.replace(data.redirect);
        } else {
          window.location.reload();
        }
      }
    });
  }
</script>
<#include "footer.ftl">
