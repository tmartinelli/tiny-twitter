<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>${t['profile.title']} - ${t['app_name']}</title>
	<link href="<c:url value='/css/profile/profile.css'/>" rel="stylesheet">
</head>
<body>
	<div class="content">
		<div class="avatarcontainer">
			<img src="http://www.croop.cl/UI/twitter/images/carl.jpg" alt="avatar" class="avatar">
		</div>			
		<div class="data">
			<ul>
				<li>2.934 <span>${t['profile.tweets']}</span></li>
				<li>1.119 <span>${t['profile.followers']}</span></li>
				<li>530 <span>${t['profile.following']}</span></li>
			</ul>
		</div>
		
		<c:if test="${not user.equals(loggedUser.user)}">
			<c:choose>
				<c:when test="${user.isFollowedBy(loggedUser.user)}">
					<form action="<c:url value='/following/${user.id}' />" method="post">
						<input type="hidden" name="_method" value="delete" />
						<button type="submit" class="action-btn unfollow">${t['profile.unfollow']}</button>
					</form>
				</c:when>
				<c:otherwise>
					<form action="<c:url value='/following' />" method="post">
						<input type="hidden" name="user.id" value="${user.id}" />
						<button type="submit" class="action-btn follow">${t['profile.follow']}</button>
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
</body>
</html>