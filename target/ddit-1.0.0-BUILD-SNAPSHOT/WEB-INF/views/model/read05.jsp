<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>READ05</h1>

<h4>주소</h4>
<p>우편번호: ${memberVO.addressVO.zonecode}</p>
<p>상세주소1: ${memberVO.addressVO.address}</p>
<p>상세주소2: ${memberVO.addressVO.buildingName}</p>

<h4>보유카드</h4>
<c:forEach var="cardVO" items="${memberVO.cardVOList}" varStatus="stat"> <!-- var: 한 개 -->
    <p>[ ${stat.count}번 카드 ]</p>
    <p>카드번호: ${cardVO.no}</p>
    <p>유효기간: ${cardVO.validMonth}</p>
</c:forEach>
