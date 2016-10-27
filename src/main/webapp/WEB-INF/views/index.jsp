<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>
<body>
	<%@include file="includes/homeRefference.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<a href="getAllDomains">Domains</a>
			</div>
			<div class="col-sm-6">
				<img alt="" src="img/web.png">
			</div>
		</div>
	</div>
</body>
</html>