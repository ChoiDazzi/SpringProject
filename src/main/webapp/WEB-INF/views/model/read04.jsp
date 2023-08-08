<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>RESULT</h1>
<h4>CarArray</h4>
<c:forEach var="car" items="${carArray}">
    <p>${car}</p>
</c:forEach>

<h4>CarList</h4>
<c:forEach var="car" items="${carList}">
    <p>${car}</p>
</c:forEach>