<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>아이디: ${memberVO.userId}</p>
<p>이름: ${memberVO.userName}</p>
<p>비밀번호: ${memberVO.password}</p>
<p>소개: ${memberVO.introduction}</p>
<c:forEach items="${memberVO.hobbys}" var="hobby">
    ${hobby}<br/>
</c:forEach>

<p> 성별 : ${memberVO.gender} </p>
<p> 국적 : ${memberVO.nationality} </p>

<c:forEach items="${memberVO.cars}" var="car">
    ${car}<br/>
</c:forEach>

