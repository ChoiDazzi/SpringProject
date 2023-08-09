<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    input, textarea {
        display: block;
    }
</style>

<h1>스프링 폼 태그 라이브러리</h1>

<form:form modelAttribute="memberVO" method="post" action="/member/registerForm08Post">
    <!-- path : vo의 멤버변수 -->
    유저ID : <form:input path="userId" />
    이름 : <form:input path="userName"/>
    비밀번호 : <form:password path="password"/>
    <form:textarea path="introduction"></form:textarea>
    취미 (hobbys): <br />
    <form:checkboxes path="hobbys" items="${hobbyMap}"/>
<%--    <form:checkbox path="hobbys" value="reading" label="reading" />--%>
<%--    <form:checkbox path="hobbys" value="swimming" label="swimming" />--%>
<%--    <form:checkbox path="hobbys" value="camping" label="camping" />--%>
    <form:radiobuttons path="gender" items="${genderMap}" />
    국적 :
    <form:select path="nationality" items="${nationalityMap}" />
    <br />
    자동차 :
    <form:select path="cars" items="${carsMap}" multiple="true" />
    <form:button name="register">등록</form:button>

</form:form>

