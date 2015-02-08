<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Home - Tiny Twitter</title>
	<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
</head>

<body>
	<h1>Bem vindo!</h1>
	<a href="${linkTo[AuthenticationController].logout}">Logout</a>
	${loggedUser.name}
</body>
</html>