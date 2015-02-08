<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>${t['page.error.title']} - ${t['app_name']}</title>
	<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
</head>
<body>
	<p>${t['page.error.message']}</p>
	<a href="${linkTo[HomeController].home}">${t['page.home.message.click']}</a> ${t['page.home.message']}
</body>
</html>