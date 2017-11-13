<%@ page import="app.model.Post" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>



<c:if test = "${supportedPosts != null}">
  <table>
    <thead>
      <tr>
        <th>Titill</th>
        <th>Dagsetning</th>
        <th>Vegur</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${supportedPosts}" var="post">
      <tr>
        <td>${post.getTitle()}</td>
        <td>${post.getDating()}</td>
        <td>${post.getRoad().getName()}</td>
        <td>
          <a class="support-toggle">
            <i class="${post.getId()} fa fa-thumbs-o-up" aria-hidden="true"></i>
          </a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>