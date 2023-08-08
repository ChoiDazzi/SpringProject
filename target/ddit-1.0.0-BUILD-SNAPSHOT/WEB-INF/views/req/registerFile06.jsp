<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  input {
    display: block;
  }
</style>
<form action="/req/registerFile06Post" method="post" enctype="multipart/form-data">
  <input type="text" name="userId" value="dazzi" />
  <input type="text" name="password" value="java" />
  <input type="file" name="pictures" multiple />
  <input type="button" id="uploadBtn" value="FILE UPLOAD">
</form>
<script src="/resources/js/jquery-3.6.0.js"></script>

<script>
  $("#uploadBtn").on("click", function () {
    let formData = new FormData();
    let pictures = $("input[name='pictures']");
    let files = pictures[0].files; // 요소 안에 들어온 파일 객체들

    for (let i=0; i<files.length; i++) {
      if (!checkExtension(files[i].name, files[i].size)) { //false
        return false;
      }
      formData.append("pictures", files[i]);
    }
    formData.append("userId", $("input[name='userId']").val());
    formData.append("password", $("input[name='password']").val());
    $.ajax({
      url:'/req/registerFile06Post',
      type:'post',
      data: formData,
      processData: false,
      contentType: false,
      dataType: 'text',
      success: function(res) {
        console.log(res);
      }
    })
  });
  //정규식
  let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
  let maxSize = 5242880; //5MB

  function checkExtension(filename, fileSize) {
    if (fileSize >= maxSize) {
      alert("파일 사이즈가 초과되었습니다.");
      return false;
    }
    if (regex.test(filename)) {
      alert("해당 종류의 파일은 업로드할 수 없습니다.");
      return false;
    }
    return true;
  }
</script>
