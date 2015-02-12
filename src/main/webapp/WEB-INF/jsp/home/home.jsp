<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>${t['home.title']} - ${t['app_name']}</title>
    <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/jquery.autocomplete.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/home/home.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/timeline.css'/>" rel="stylesheet">
  </head>

  <body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">${t['app_name']}</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${linkTo[HomeController].home}">${t['home.title']}</a></li>
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
			
			<ul class="timeline">		
				<c:forEach var="tweet" items="${user.timeline}">
					<li>
						<div class="avatar">
		          			<img src="http://www.croop.cl/UI/twitter/images/doug.jpg">
						</div>
						<div class="bubble-container">
							<div class="bubble">
								<h3>${tweet.userName}</h3> ${format.localDateTime(tweet.dateTime)} <br>
								${tweet.content}
							</div>
							
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
    </div><!-- /.container -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="<c:url value='/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.autocomplete.min.js'/>"></script>
    <script src="<c:url value='/js/quick-search.js'/>"></script>
    
  </body>
</html>
