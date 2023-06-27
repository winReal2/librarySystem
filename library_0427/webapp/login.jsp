<%@page import="com.utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<!-- resolved 에러가 났다면 java build path 에 에러가 난 파일이있는지 확인 후 remove 후 재실행 해보거라 -->
				<%
				
			    	// 로그인 실패시 메세지 처리
			    	String error = request.getParameter("error");
			    	if(error != null &&
			    			"Y".equals(error)){
			    		out.print("아이디/비밀번호를 확인해주세요!!<br><br>");
			    	}

			    	String userId = CookieManager.readCookie(request, "userId");

		    	%>
			    
			    <%
			    	String name = request.getParameter("name");
			    	if(name != null && !name.equals("")){
			    		// 로그인 되었다고 판단
			    		out.print("<h1>" + name + " 님 환영 합니다.</h1>");

			    	} else {
			    		
			    %>	
			<aside id='rightside'>
				<%-- <form action="./loginAction.jsp" method="post">--%>				    
				<form action="./login/LoginAction.do" method="post">				    
                    <div class='loginbox'>
                        <div id='login'>
                            <input type="text" name="userid" id="userid" placeholder='ID를 입력해주세요.' required="required"
                            								value="<%=userId %>">
                            <input type="password" name="userpw" id="userpw" placeholder='PW를 입력해주세요.' required="required">
                        </div>
                        <div id='button'>
                        <input type="submit" value="로그인">
                        </div>
                    </div>
           
                    <div id='info'>
                        <input type="checkbox" name="save_check" value="Y" <%=!userId.equals("") ? "checked" : "" %>>아이디 저장하기<br>
                        <a href="">회원가입</a>
                        <a href="">ID찾기</a>
                        <a href="">PW찾기</a>
                    </div>
                
		    		</form>
	    		<% } %>
    		  </aside>

</body>
</html>