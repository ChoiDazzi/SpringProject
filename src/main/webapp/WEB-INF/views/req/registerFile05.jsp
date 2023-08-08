<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  input {
    display: block;
  }
</style>
<form action="/req/registerFile04Post" method="post" enctype="multipart/form-data">
  <input type="text" name="userId" value="dazzi" />
  <input type="text" name="password" value="java" />
  <input type="file" name="pictures" id="inputFile" />
  <input type="submit" value="FILE UPLOAD">
</form>
<script src="/resources/js/jquery-3.6.0.js"></script>

<script>
  $("#inputFile").on("change", function() {
    let userId = $("input[name='userId']").val();
    let password = $("input[name='password']").val();
    //files 속성은 파일 입력(input type="file") 요소에서 선택된 파일들의 목록
    let files = event.target.files;

    let file = files[0];

    let formData = new FormData(); //가상의 <form></form>
    formData.append("file", file); //<form> <input type="file" name="file" /> </form>
    formData.append("userId", userId);
    formData.append("password", password);
    $.ajax({
      url:'/req/registerFile05Post',
      type:'post',
      data: formData,
      processData: false,
      contentType: false,
      dataType: 'text',
      success: function(res) {
        console.log(res);
      }
    })
  })
</script>
