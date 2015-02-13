<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>${t['profile.title']} - ${t['app_name']}</title>
	<link href="<c:url value='/css/profile/profile.css'/>" rel="stylesheet">
</head>
<body>
	<div class="content">
		<div class="avatarcontainer">
			<img src="<c:url value='/img/avatar/${user.avatar}' />" alt="avatar" class="avatar">
		</div>			
		<div class="data">
			<ul>
				<li>- <span>${t['profile.tweets']}</span></li>
				<li>- <span>${t['profile.followers']}</span></li>
				<li>- <span>${t['profile.following']}</span></li>
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
	
	<tt:timeline tweets="${user.tweets}" />
</body>
</html>