<#-- @ftlvariable name="cList" type="java.util.List" -->
<#include "header.ftl">
<#if cList??>
  <#list cList>
  <table border="1" width="438" class="center">
    <tr>
      <th>序号</th>
      <th>分类名称</th>
      <th>分类描述</th>
      <th>操作</th>
    </tr>
  <#items as i>
    <tr class="${i?item_parity}">
      <th>${i?index + 1}</th>
      <th>${i.name}</th>
      <th>${i.description}</th>
      <th>
        <a onclick="return false;" href="/manage/category/edit/${i.id}">编辑</a>
        <a onclick="return delById(${i.id});" href="#">删除</a>
      </th>
    </tr>
  </#items>
  </table>
  <#else>
NO items 1
  </#list>
<#else>
NO items 2
</#if>
<script type="text/javascript">
  function delById(id) {
    $.post({
      url: "${rc.contextPath}/manage/category/del",
      data: JSON.stringify({"id":id}),
      contentType: "application/json",
      success: function (data, textStatus) {
        if (data.redirect) {
          window.location.replace(data.redirect);
        }
        else {
          window.location.reload();
        }
      }
    });
  }

</script>
<#include "footer.ftl">
