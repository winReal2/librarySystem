<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<!-- 검색폼 시작--> 
    <form method="get" name='searchForm' >
    
    <!-- 페이지 번호 -->
    <input type='text' name='pageNo'>
    <input type='text' name='delNo'>
    <%-- <input type='hidden' name='pageNo' value="${param.pageNo}" /> --%>
    
    
    <table border="1" width="100%" >
    <tr>
        <td align="center">
        	<%-- 확인해보기
        	searchField : [${ param.searchField }]
        	비교 결과  : [${ param.searchField eq "content" }]
        	--%> 
            <select name="searchField">
                <option value="title" ${ param.searchField eq "title" ? "selected" : ""} >도서명</option> 
                <option value="author" ${ param.searchField eq "author" ? "selected" : ""} > 저자명 </option>
            </select>
            <input type="text" name="searchWord" value="${param.searchWord}" />
            <input type="submit" value="검색하기" />
        </td>
    </tr>   
    </table>
    </form>
	<!-- 검색폼 끝--> 

</body>
</html>