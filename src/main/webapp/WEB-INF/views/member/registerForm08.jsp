<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>스프링 폼 태그 라이브러리</h1>
<pre>
스프링 폼은 HTML 폼을 표시하기 위한 태그 라이브러리.
스프링 폼을 이용하면 HTML 폼(뷰, jsp)과 자바 객체(Controller의..)를 쉽게 바인딩(연결)할 수 있음
</pre>
<form:form modelAttribute="memberVO" method="post"
           action="/member/registerForm08Post">
<%--     <form:hidden path="userId" /> --%>
    <form:input path="userId" />
    <!-- 만약 유효성 검증 실패 시 -->
    <font color="tomato"><form:errors path="userId" /></font>
    <p>
        <form:label path="userName">이름: </form:label>
        <form:input path="userName"/>
        <!-- 만약 유효성 검증 실패 시 -->
        <font color="tomato"><form:errors path="userName" /></font>
    </p>
    <p>
        <form:label path="password">비밀번호 : </form:label>
        <form:password path="password"/>
    </p>
    <p>
        <form:label path="email">이메일 : </form:label>
        <form:input path="email"/>
        <font color="tomato"><form:errors path="userName" /></font>
    </p>
    <p>
        <form:label path="dateOfBirth">생일</form:label>
        <form:input path="dateOfBirth" placeholder="2020-01-01"/>
        <font color="tomato"><form:errors path="dateOfBirth" /></font>
    </p>
    <p>
        우편번호: <form:input path="addressVO.zonecode"/>
        <font color="tomato">
            <form:errors path="addressVO.zonecode" />
        </font>
        <br>
        주소: <form:input path="addressVO.address"/>
        <font color="tomato">
            <form:errors path="addressVO.address" />
        </font>
        <br>
        상세주소: <form:input path="addressVO.buildingName"/>
        <font color="tomato">
            <form:errors path="addressVO.buildingName" />
        </font>
    </p>
    <p>
        <form:textarea path="introduction"/>
    </p>
    <p>
        <form:label path="hobbys">취미 : </form:label>
        <br />
            <%--       <form:checkboxes items="${hobbyMap}" path="hobbys" /> --%>
        <form:checkbox path="hobbys" value="sports" label="sports" />
        <form:checkbox path="hobbys" value="music" label="music" />
        <form:checkbox path="hobbys" value="movie" label="movie" />
    </p>
    <p>
        성별 :
        <!-- 여러 개의 라디오 버튼 요소 radiobuttons
        items : value와 label을 자동 구성
        path : id와 name을 구성
        -->
            <%--    <form:radiobuttons path="gender" items="${genderMap}" /> --%>
        <form:radiobutton path="gender" value="Female" label="Female" />
        <form:radiobutton path="gender" value="Male" label="Male" />
        <form:radiobutton path="gender" value="Other" label="Other" />
    </p>
    <p>
        국적 :

        <form:select path="nationality" items="${nationalityMap}" />
    </p>
    <p>
        자동차 :
        <form:select path="cars" items="${carsMap}" multiple="true" />
    </p>
    <p>
            <%--       <form:button name="register">등록</form:button> --%>
        <button type="submit">등록</button>
        <button type="button">목록</button>
        <input type="reset" value="초기화" />
    </p>
</form:form>