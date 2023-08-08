<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  input {
    display: block;
  }
</style>
<form action="/req/registerFile02Post" method="post" enctype="multipart/form-data">
  <input type="text" name="userId" value="userId" />
  <input type="text" name="password" value="password" />
  <input type="file" name="picture" />
  <input type="submit" value="FILE UPLOAD">
</form>
