<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/js/jquery-3.6.0.js"></script>
<h3>register</h3>
<p>
<form action="/board/post" method="post">
    <input type="text" name="bookId" value="ISBN1234" />
    <button type="submit" name="register">REGISTER</button>
    <button type="submit" name="update">UPDATE</button>
    <button type="submit" name="delete">DELETE</button>
    <button type="submit" name="list">LIST</button>
    <br>
    <button type="submit" name="remove">REMOVE</button>
    <button type="button" id="btnHeaders">Headers Mapping</button>
    <a href="/board/get?read" class="btn btn-primary">READ</a>
</form>
</p>

<script>
    $("#btnHeaders").on("click", function (){
        let boardNo = "10";
        let title = "모순";
        let content = "모순 - 양귀자";
        let writer = "양귀자";

        let data = {
            "boardNo": boardNo,
            "title": title,
            "content": content,
            "writer": writer
        };

        //JSON: Object => String 변환
        //JSON(Java Object Notation): 클라이언트(브라우저)와 서버 간 데이터(텍스트)를 교환할 때 활용.
        console.log("data: ", JSON.stringify(data));

        $.ajax({
            url:'/board/' + boardNo,
            contentType:'application/json;charset=utf-8',
            data: JSON.stringify(data),
            type: 'post',
            success: function(result) {
                console.log("result: ", result);
            }
        })
    });
</script>
