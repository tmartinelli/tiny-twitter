<?xml version="1.0" encoding="UTF-8" ?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title><decorator:title /></title>
	<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/jquery.autocomplete.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/timeline.css'/>" rel="stylesheet">
	<decorator:head />
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">${t['app_name']}</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${linkTo[HomeController].home}">${t['home.title']}</a></li>
					<li><a href="${linkTo[AuthenticationController].logout}">${t['logout']}</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><p class="navbar-text">${loggedUser.name}</p></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" name="country" id="searchInput" class="form-control" placeholder="${t['home.search.placeholder']}" />
				</form>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">
		<decorator:body />
	</div><!-- /.container -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/js/jquery.autocomplete.min.js'/>"></script>
	<script src="<c:url value='/js/quick-search.js'/>"></script>
	<decorator:getProperty property="page.local_script"></decorator:getProperty>
</body>
</html>
