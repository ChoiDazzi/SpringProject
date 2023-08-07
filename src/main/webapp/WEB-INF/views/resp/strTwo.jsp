<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="/resources/js/jquery-3.6.0.js"></script>
<h1>Return String Two</h1>

<!-- 1) 로그인 2) 로그인 계정 == 작성자 계정 -->
<p>
    <button type="button" id="edit" style="display: none;">EDIT POST</button>
</p>
<p>
    <select name="category" id="sel1">
        <option value="">CATEGORY</option>
    </select>
</p>

<script>
    $.ajax({
        url: "/resp/returnRES",
        type: "get",
        dataType: "text",
        success: function(res) {
            console.log(res);
            if (res == "SUCCESS") {
                $("#edit").css("display", "block");
            }
        }
    });

    $.ajax({
        url: "/resp/returnREL",
        type: "get",
        dataType: "json", //응답타입
        success: function (res) {
            console.log("result: ", JSON.stringify(res));
            $.each(res, function(i,v) {
                $("#sel1").append(`<option value='\${v}'>\${v}</value>`);
            })
        }
    })

</script>