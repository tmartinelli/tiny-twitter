<%@ tag %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="tweets" required="true" type="java.util.Collection<br.com.tmartinelli.tinytwitter.domain.tweet.Tweet>" description="the tweet list." %>

<c:if test="${not empty tweets}">	
	<ul class="timeline">		
		<c:forEach var="tweet" items="${tweets}">
			<li>
				<div class="avatar">
          			<a href="<c:url value='/users/${tweet.user.id}' />">
	          			<img src="<c:url value='/img/avatar/${tweet.user.avatar}' />">
          			</a>
				</div>
				<div class="bubble-container">
					<div class="bubble">
						<h3>${tweet.user.name}</h3> ${format.localDateTime(tweet.dateTime)} <br>
						${tweet.content}
					</div>
					
				</div>
			</li>
		</c:forEach>
	</ul>
</c:if>