<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dummy Page</title>
<style>  
h3{
  font-family: Calibri; 
  font-size: 25pt;         
  font-style: normal; 
  font-weight: bold; 
  color:SlateBlue;
  text-align: center; 
  text-decoration: underline
}
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

</style> 
</head>
<body>

	<h3>Your AAL platform</h3>
	<%
		
		 response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		
		if(session.getAttribute("user")==null)
		{
			response.sendRedirect("/login");
		}
	
	%>
	Welcome, ${sessionScope.user.user_fname } ${sessionScope.user.user_lname }
	<br>

	<div>
		Your temperature is ok: 36.5°C
	</div>
	<div>
		Your blood pressure is normal: Systolic BP: 124 mmHg	Diastolic BP: 78 mmHg
	</div>

	<div>
		Humidity in your house is: 56%
	</div>
	<div>
		Temperature in your house is: 22.5°C
	</div>

	<div>
		All data looks good!
	</div>
	
	<a href="${pageContext.request.contextPath }/logout">Logout</a>
</body>
</html>

