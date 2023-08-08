<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  input {
    display: block;
  }
</style>
<form action="/req/registerFile01Post" method="post" enctype="multipart/form-data">
  <input type="file" name="picture" />
  <input type="submit" value="FILE UPLOAD">
</form>
