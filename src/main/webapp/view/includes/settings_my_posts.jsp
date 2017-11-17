<%@ page import="app.model.Post" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
  <c:when test = "${userPosts == null || userPosts.isEmpty()}">
    <p>Þú átt engin innlegg</p>
  </c:when>
  
  <c:otherwise>
    <table>
      <thead>
        <tr>
          <th>Titill</th>
          <th>Dagsetning</th>
          <th>Vegur</th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        
        <c:forEach items="${userPosts}" var="post">
          <tr>
            <td>${post.getTitle()}</td>
            <td>${post.getDating()}</td>
            <td>
              ${post.getRoad().getName()}
              <i class="fa fa-info-circle" aria-hidden="true" title="${post.getRoad().toString()}"></i>
            </td>
            <td>
              <a class="archive-post-btn" aria-label="Mark as archived" title="Merkja sem afgreitt">
                <c:choose>
                    <c:when test='${post.isArchived()}'>
                       <i class="${post.getId()} archived fa fa-check-circle-o" aria-hidden="true"></i>
                    </c:when>
                    <c:otherwise>
                       <i class="${post.getId()} fa fa-circle" aria-hidden="true"></i>
                    </c:otherwise>
                </c:choose>
              </a>
            </td>
            <td>
              <a class="delete-post" aria-label="Delete" title="Eyða innleggi">
                <i class="${post.getId()} fa fa-trash-o" aria-hidden="true"></i>
              </a>
            </td>
          </tr>
        </c:forEach>
          
      </tbody>
    </table>
  </c:otherwise>
</c:choose>
    
