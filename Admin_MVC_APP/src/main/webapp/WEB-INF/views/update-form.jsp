<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>Admin Registration Form</h2>
		<form:form action="update" modelAttribute="admin" method="post">
			<form:label path="id">Id</form:label>
			<form:input path="id" placeholder="Enter admin Id" />
			<br>
			<form:label path="name">Name</form:label>
			<form:input path="name" placeholder="Enter admin Name" />
			<br>
			<form:label path="phone">Phone</form:label>
			<form:input path="phone" placeholder="Enter admin Phone" />
			<br>
			<form:label path="email">Email</form:label>
			<form:input path="email" placeholder="Enter admin Email" />
			<br>
			<form:label path="gender">Gender</form:label>
			<form:radiobutton path="gender" value="male" />Male
			<form:radiobutton path="gender" value="female" />FeMale
			<br>
			<form:label path="password">Password</form:label>
			<form:input path="password" placeholder="Enter admin Password" />
			<br>
			<form:button>SAVE ADMIN</form:button>
		</form:form>
	</div>
</body>
</html>