<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>${t['home.title']} - ${t['app_name']}</title>
    <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/css/home/home.css'/>" rel="stylesheet">
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
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
        	<input type="text" class="form-control" placeholder="Search...">
          </form>
        </div><!--/.nav-collapse -->
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
			
			<c:forEach var="tweet" items="${user.timeLine}">
				<div class="content">
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
							${tweet.userName} - ${format.localDateTime(tweet.dateTime)}
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">${tweet.content}</div>
					</div>
				</div>
			</c:forEach>
		</div>
    </div><!-- /.container -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="<c:url value='/js/bootstrap.min.js'/>"></script>
  </body>
</html>
