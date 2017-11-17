<%@ page import="app.model.Post" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
  <c:when test = "${supportedPosts == null || supportedPosts.isEmpty()}">
    <p>Þú hefur ekki stutt nein innlegg</p>
  </c:when>
    
  <c:otherwise>
    <table>
      <thead>
        <tr>
          <th>Titill</th>
          <th>Dagsetning</th>
          <th>Vegur</th>
          <th>Afgreitt</th>
        </tr>
      </thead>
      <tbody>

        <c:forEach items="${supportedPosts}" var="post">
          <tr class="${post.getId()}">
            <td>${post.getTitle()}</td>
            <td>${post.getDating()}</td>
            <td>${post.getRoad().getName()}</td>
            <td>
              <c:choose>
                <c:when test = "${!post.isArchived()}">
                  Nei
                </c:when>
                <c:otherwise>
                  Já
                </c:otherwise>
              </c:choose>                
            </td>
          </tr>
        </c:forEach>

      </tbody>
    </table>
  </c:otherwise>
</c:choose>