<#include "header.ftl">
    <form action="${rc.contextPath}/manage/category/add" method="post"  datatype="json" id="addForm">
      <table border="1" width="438" class="center">
      <#--<table border="1" width="438" class="center">-->
        <tr>
          <td>*分类名称：</td>
          <td>
            <input type="text" name="name" id="name"/>
          </td>
        </tr>
        <tr>
          <td>描述:</td>
          <td>
            <textarea rows="3" cols="38" name="description"></textarea>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <input type="button" value="保存" onclick="toSubmit()"/>
          </td>
        </tr>
      </table>
    </form>
    <script type="text/javascript">
      function toSubmit() {
        var nameValue = document.getElementById("name").value;
        if (nameValue.trim() == "") {
          alert("请输入分类名称");
          return;
        }
        var value = $("#addForm").serializeArray();
        var o = {};
        $.each(value,function(index){
          if(o[this['name']]){
            o[this['name']] = o[this['name']] + ","+this['value'];
          }else{
            o[this['name']] = this['value'];
          }
        });
        var s = JSON.stringify(o);
        // alert(s);
        $.ajax({
          type: "POST",
          url: "${rc.contextPath}/manage/category/add",
          data: s,
          // dataType: "json",
          contentType : "application/json",

          success: function(data, textStatus) {
            if (data.redirect) {
              // data.redirect contains the string URL to redirect to
              window.location.replace(data.redirect);
            }
            else {
              alert("textStatus:"+textStatus);
              for (var dataKey in data) {
                console.log(dataKey+":"+data[dataKey])
              }
            }
          }
        });
      }
    </script>
<#include "footer.ftl">
