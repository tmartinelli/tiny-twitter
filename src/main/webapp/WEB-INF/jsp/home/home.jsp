<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>${t['home.title']} - ${t['app_name']}</title>
	<link href="<c:url value='/css/home/home.css'/>" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty message}">
		<div class="alert alert-success alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<p class="text-center">${t[message]}</p>
		</div>		
	</c:if>
	
	<c:if test="${not empty errors}">
		<div class="alert alert-danger" role="alert">
			<c:forEach var="error" items="${errors}">
				<p class="text-center">${error.message}</p>
			</c:forEach>
		</div>
	</c:if>
	
	<div class="home">
		<form action="<c:url value='/tweets'/>" method="post" class="form-inline">
			<input type="text" name="content" class="form-control" id="tweetBox" placeholder="${t['home.content.placeholder']}">
			<button type="submit" class="btn btn-primary">${t['home.send']}</button>
		</form>
		
		<br />
		
		<tt:timeline tweets="${user.timeline}" />
	</div>
</body>
</html>
