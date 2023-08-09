<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Spring Form Tag Library</h1>
<pre>
  스프링 폼은 HTML 폼을 표시하기 위한 태그 라이브러리.
  스프링 폼을 이용하면 HTML 폼(view, jsp)과 자바 객체(Controller...)를 쉽게 바인딩(연결)할 수 있음
</pre>
<form:form modelAttribute="memberVO" method="post" action="/member/register">
  <!-- path: vo의 멤버변수 -->
  ID:   <form:input path="userId"/> <br>
  NAME: <form:input path="userName"/> <br>
  <form:button name="register">등록</form:button>
</form:form>

