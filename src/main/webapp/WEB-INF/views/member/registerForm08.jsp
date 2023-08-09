<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    input {
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
    <form:button name="register">등록</form:button>
</form:form>
