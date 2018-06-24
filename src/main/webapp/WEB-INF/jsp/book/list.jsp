<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Book list</title>

<meta name="viewport" content="width=device-width" />
<base href="/" />
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/webjars/font-awesome/css/font-awesome.min.css"></link>
<link rel="stylesheet" type="text/css" href="css/custom.css" />
</head>
<body>
	<%@include file="/WEB-INF/jsp/elements/menu.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1>BOOK LIST</h1>
			</div>
		</div>

		<c:forEach items="${books}" var="book">
			<div class="row borderBottom bookNode">
				<div class="col-xs-3">${book.name}</div>
				<div class="col-xs-3">${book.author.name}</div>
				<div class="col-xs-3">${book.isbnCode}</div>
				<div class="col-xs-3">
					<a href="print?id=${book.id}"class="btn btn-primary">PRINT</a>
					<%/*<form:form action="print" method="POST">
						<form:hidden path="${bookname}"/>
						<button type="submit" class="btn btn-primary"></button>
					</form:form>*/%>
				</div>
			</div>
		
   		 ${item}<br>
		</c:forEach>

	</div>


	<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>