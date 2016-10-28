<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Ihor Maiba">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Domain Information</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>
<body class=".container-fluid">
	<%@include file="includes/homeRefference.jsp" %>
	<div class="container myrow-container">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">Domain Details</h3>
			</div>
			<div class="panel-body">
				<form:form id="domainRegisterForm" cssClass="form-horizontal"
					modelAttribute="domain" method="post">

					<div class="form-group">
						<div class="control-label col-xs-3">
							<form:label path="domainName">Domain name</form:label>
						</div>
						<div class="col-xs-6">
							<form:hidden path="id" value="${domainObject.id}" />
							<form:errors path="domainName" class="error"/>
							<form:input cssClass="form-control" path="domainName"
								value="${domainObject.domainName}" />
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-xs-4"></div>
							<div class="col-xs-4">
								<input type="submit" id="saveDomain" class="btn btn-primary"
									value="Save" onclick="return submitDomainForm();" />
							</div>
							<div class="col-xs-4"></div>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/submitDomainForm.js"></script>
</body>
</html>