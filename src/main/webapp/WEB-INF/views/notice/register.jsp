<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3>noticeRegister: 로그인한 회원만 접근 가능 </h3>
<form action="/logout" method="post">
    <button type="submit">로그아웃</button>
    <sec:csrfInput/>
</form>