<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="is">
  <head>
    <meta charset="utf-8">
    <title>Vegavaktin</title>
    <link href="<c:url value="/css/navigation.css" />" rel="stylesheet">
  </head>
  <body>
    <main>
      <jsp:include page="includes/nav.jsp" />
      <h3>${message}</h3>
    </main>
    <jsp:include page="includes/footer.jsp" />
  </body>
</html>
