<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>${t['profile.title']} - ${t['app_name']}</title>
		<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/jquery.autocomplete.css'/>" rel="stylesheet">
		<link href="<c:url value='/css/profile/profile.css'/>" rel="stylesheet" >
		<link href="<c:url value='/css/timeline.css'/>" rel="stylesheet" >
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
		<div class="content">
			<div class="avatarcontainer">
				<img src="http://www.croop.cl/UI/twitter/images/carl.jpg" alt="avatar" class="avatar">
			</div>			
			<div class="data">
				<ul>
					<li>2,934 <span>Tweets</span></li>
					<li>1,119 <span>Followers</span></li>
					<li>530 <span>Following</span></li>
				</ul>
			</div>
			
			<c:if test="${not user.equals(loggedUser.user)}">
				<c:choose>
					<c:when test="${user.isFollowedBy(loggedUser.user)}">
						<form action="<c:url value='/following/${user.id}' />" method="post">
							<input type="hidden" name="_method" value="delete" />
							<button type="submit" class="action-btn unfollow">Unfollow</button>
						</form>
					</c:when>
					<c:otherwise>
						<form action="<c:url value='/following' />" method="post">
							<input type="hidden" name="user.id" value="${user.id}" />
							<button type="submit" class="action-btn follow">Follow</button>
						</form>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
		
		<br />
			
		<ul class="timeline">		
			<c:forEach var="tweet" items="${user.tweets}">
				<li>
					<div class="avatar">
	          			<img src="http://www.croop.cl/UI/twitter/images/carl.jpg">
					</div>
					<div class="bubble-container">
						<div class="bubble">
							<h3>${user.name}</h3> ${format.localDateTime(tweet.dateTime)} <br>
							${tweet.content}
						</div>
						
					</div>
				</li>
			</c:forEach>
		</ul>

	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/js/jquery.autocomplete.min.js'/>"></script>
	<script src="<c:url value='/js/quick-search.js'/>"></script>

</body>
</html>