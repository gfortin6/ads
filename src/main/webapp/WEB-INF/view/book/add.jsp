<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Add a book</title>

<meta name="viewport" content="width=device-width" />
<base href="/" />
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/css/bootstrap.min.css" />

<link rel="stylesheet"
	href="/webjars/font-awesome/css/font-awesome.min.css"></link>
<link rel="stylesheet" type="text/css" href="css/custom.css" />

</head>
<body>
	<%@include file="/WEB-INF/view/elements/menu.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1>Add a book</h1>
			</div>
		</div>
		<form:form action="processAddBook" method="POST" modelAttribute="book">
			<div class="row">
				<div class="form-group">
					<label class="control-label col-xs-6 col-sm-3 col-md-2">Author</label>
					<div class="col-xs-6 col-sm-3">
						<form:select path="author" class="form-control" multiple="false">
							<form:options items="${authors}" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="row">

				<div class="form-group">
					<label class="control-label col-xs-6 col-sm-3 col-md-2">Name
						*</label>
					<div class="col-xs-6 col-sm-3">
						<form:input path="name" class="form-control" maxlength="120" />
						<form:errors path="name" cssClass="error"></form:errors>
					</div>
				</div>
			</div>

			<div class="row">

				<div class="form-group">
					<label class="control-label col-xs-6 col-sm-3 col-md-2">IsbnCode
						*</label>
					<div class="col-xs-6 col-sm-3">
						<form:input path="isbnCode" class="form-control" />
						<form:errors path="isbnCode" cssClass="error"></form:errors>
					</div>
				</div>
			</div>

			<div class="row">

				<div class="form-group">
					<input type="submit" class="btn btn-primary" value="Add" />
				</div>
			</div>

		</form:form>
	</div>
	<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>