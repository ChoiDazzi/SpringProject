<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>register</h3>
<p>
<form action="/board/post" method="post">
    <input type="text" name="bookId" value="ISBN1234" />
    <button type="submit" name="register">REGISTER</button>
    <button type="submit" name="update">UPDATE</button>
    <button type="submit" name="delete">DELETE</button>
    <button type="submit" name="list">LIST</button>
</form>
</p>
