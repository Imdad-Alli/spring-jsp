<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>Admin Details</h2>
		<p>${succMsg}</p>
		<table>
			<tr>
				<th>Admin Id</th>
				<th>Admin name</th>
				<th>Admin phone</th>
				<th>Admin email</th>
				<th>Admin gender</th>
				<th>Admin password</th>
			</tr>
			<tr>
				<td>${admin.id}
				<td>${admin.name}
				<td>${admin.phone}
				<td>${admin.email}
				<td>${admin.gender}
				<td>${admin.password}
			</tr>
		</table>
	</div>
</body>
</html>