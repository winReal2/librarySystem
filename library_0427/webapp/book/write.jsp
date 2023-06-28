<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<!-- form에서 action주는 것과  input 태그 button에서 onclick하는 것은 무슨 차이? -->
<form name="writeFrm" method="post" enctype='multipart/form-data' 
		action="./write.book" onsubmit="return validateForm(this);">
<%@include file="../common/header.jsp" %>
<h2>책 등록하기</h2>
    <table border="1" width="90%">

        <tr>
            <td>제목</td>
            <td>
                <input type="text" name="title" style="width: 150px;" value="자바의 정석"/>
            </td>
        </tr>
        <tr>
            <td>작가</td>
            <td>
                <input type="text" name="author" style="width: 150px;" value="남궁성"/>
            </td>
        </tr>
        <tr>
            <td>책 이미지</td>
            <td>
                <input type="file" name="bookImg" />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">작성 완료</button>
                <button type="reset">다시 입력</button>
                <button type="button" onclick="location.href='list.book'">목록 보기</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>