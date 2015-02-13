<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>${t['login.title']} - ${t['app_name']}</title>
	<link href="<c:url value='/css/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/css/authentication/login.css' />" rel="stylesheet">
</head>

<body>
	<c:if test="${not empty errors}">
		<div class="alert alert-danger" role="alert">
			<c:forEach var="error" items="${errors}">
			    <p class="text-center">${error.message}</p>
			</c:forEach>
		</div>
	</c:if>
	<div class="container">
		<form action="<c:url value='/login'/>" method="post" class="form-signin">
			<h2 class="form-signin-heading">${t['login.container.title']}</h2>
			
			<label for="inputEmail" class="sr-only">${t['login.email']}</label>
			<input type="email" name="user.email" value="${user.email}" id="inputEmail" class="form-control" placeholder="${t['login.email']}" required autofocus /> 
			
			<label for="inputPassword" class="sr-only">${t['login.password']}</label>
			<input type="password" name="user.password" id="inputPassword" class="form-control" placeholder="${t['login.password']}" required />
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">${t['login.button']}</button>
		</form>
	</div>
</body>
</html>