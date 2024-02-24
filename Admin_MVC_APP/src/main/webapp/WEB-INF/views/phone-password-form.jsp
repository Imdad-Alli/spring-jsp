<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="verifyByPhonePassword" method="post">
		<label for="phone">Phone</label> 
		<input type="number" name="phone" placeholder="Enter User Phone">
		 <br> 
		 <label	for="password">password</label>
		 <input type="text" name="password" placeholder="Enter User password">
		 <br> 
		 <input type="submit" value="VERIFY BY PHONE AND PASSWORD">
	</form>
</body>
</html>