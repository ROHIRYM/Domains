<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Domain List</title>
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
				<h3 class="panel-title">
					<div align="left">
						<a href="getAllDomains">Domain List</a>
					</div>
					<div align="right">
						<a href="createDomain">Add New Domain</a>
					</div>
				</h3>
			</div>
			<div class="panel-body">
				<c:if test="${empty domainAndStatusList}">
                There are no Domains
            </c:if>
				<c:if test="${not empty domainAndStatusList}">

					<form action="searchDomain">
						<div class="row">
							<div class="col-md-6">
								<div class="col-md-6">Search Domains:</div>
								<div class="col-md-6">
									<input type="text" name="searchName" id="searchName">
								</div>
							</div>
							<div class="col-md-4">
								<input class="btn btn-success" type='submit' value='Search' />
							</div>
						</div>
					</form>

					<table class="table table-hover table-bordered">
						<thead style="background-color: #bce8f1;">
							<tr>
								<th>Id</th>
								<th>Domain Name</th>
								<th>Flagged Status</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${domainAndStatusList}" var="domainAndStatus">
								<tr>
									<td><c:out value="${domainAndStatus.domain.id}" /></td>
									<td><a href="<c:out value="${domainAndStatus.domain.domainName}" />"><c:out value="${domainAndStatus.domain.domainName}" /></a></td>
									<td><c:out value="${domainAndStatus.status}" /></td>
									<td><a href="editDomain?id=<c:out value='${domainAndStatus.domain.id}'/>">Edit</a></td>
									<td><a
										href="deleteDomain?id=<c:out value='${domainAndStatus.domain.id}'/>"
										onclick="return confirm('Are you sure?')">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</div>
</body>
</html>