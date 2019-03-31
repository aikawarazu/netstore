<#include "header.ftl">
    <form  action="${rc.contextPath}/manage/category/add" method="post" enctype="multipart/form-data" id="addForm" name="addForm">
      <table border="1" width="438" class="center">
      <#--<table border="1" width="438" class="center">-->

        <tr>
          <td><label for="bookName">*书名：</label></td>
          <td><input type="text" name="bookName" id="bookName"/></td>
        </tr>
        <tr>
          <td><label for="bookAuthor">*作者：</label></td>
          <td><input type="text" name="bookAuthor" id="bookAuthor"/></td>
        </tr>
        <tr>
          <td><label for="price">*价格：</label></td>
          <td><input type="text" name="price" id="price"/></td>
        </tr>
        <tr>
          <td><label for="bookVersion">*版本：</label></td>
          <td><input type="text" name="bookVersion" id="bookVersion"/></td>
        </tr>
        <tr>
          <td><label for="publisher">*出版社：</label></td>
          <td><input type="text" name="publisher" id="publisher"/></td>
        </tr>
        <tr>
          <td><label for="categoryId">*分类名称：</label></td>
          <td>
            <#list cList>
              <select id="categoryId" name="categoryId">
              <#items as i>
                <option value="${i.id}">${i.getName()}</option>
              </#items>
              </select>
            </#list>
          </td>
        </tr>
        <tr>
          <td><label for="description">描述:</label></td>
          <td><textarea rows="3" cols="38" id="description" name="description"></textarea></td>
        </tr>
        <tr>
          <td colspan="1">封面1</td>
          <td colspan="2">
            <input type="file" name="file1" id="file1"/>
          </td>
        </tr>
        <tr>
          <td colspan="1">封面2</td>
          <td colspan="2">
            <input type="file" name="file2" id="file2"/>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <input type="button" value="提交" onclick="toSubmit()"/>
          </td>
        </tr>
      </table>
    </form>
<div id="uploadMsg"></div>
    <script type="text/javascript">
      function toSubmit() {
        // var nameValue = document.getElementById("name").value;
        // if (nameValue.trim() == "") {
        //   alert("请输入分类名称");
        //   return;
        // }
        <#if _csrf??>
          <#if _csrf.parameterName??>
            <#if _csrf.token??>

            </#if>
          </#if>
        </#if>
        var addForm = $('#addForm')[0];
        // var addForm = $('addForm')[0];
        var formData = new FormData(addForm);
        // formData.append('headPic', $("#upfile")[0].files[0]);
        $.post({                      // 上传方法
          url: '${rc.contextPath}/manage/book/add',      // 上传地址
          dataType: 'json', // 上传完成后, 返回json, text
          data: formData,
          processData: false,
          contentType: false,
          success: function (data) {
            if (data.redirect){
              window.location.replace(data.redirect);
            } else {
              var errMsg ='';
              data['fieldErrors'].forEach(function (s) {
                // s.field+":"+
                errMsg+=(s.defaultMessage +"<br>");
              });
              $('#uploadMsg').html(errMsg);
            }
          }
        });
      }
    </script>
<#include "footer.ftl">
