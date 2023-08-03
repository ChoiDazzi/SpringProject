<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="/resources/js/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<title>책 등록하기</title>
</head>
<body>
<h1>책 등록</h1>
 <form action="/create" method="post">
 	<p>제목 : <input type="text" name="title" id="title" required /></p>
 	<p>카테고리 : <input type="text" name="category" id="category" required /></p>
 	<p>가격 : <input type="number" name="price" maxLength="10" id="price" required /></p>
 	<p>설명 : <textarea rows="5" cols="30" name="content" id="content"></textarea> </p>
 	<p>
 		<input type="submit" value="저장" />
 		<input type="button" value="목록" />
 		<input type="button" id="btnHeaders" value="Headers Mapping" />
 	</p>
 </form>
<script>
	$("#btnHeaders").on("click", function() {
		let data = {
			"title": $("#title").val(),
			"category": $("#category").val(),
			"price": $("#price").val(),
			"content": $("#content").text()
		}

		$.ajax({
			url: '/board/book',
			contentType:'application/json;charset=utf-8',
			type: "post",
			data: JSON.stringify(data),
			success: function (res) {
				console.log("res", res);
			}
		});
	})
</script>
</body>
</html>