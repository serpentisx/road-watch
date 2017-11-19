<%@ page import="app.model.Post" %>
<%@ page import="app.model.Account" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<div class="posts-container">
  <c:if test = "${posts != null}">
    <c:forEach begin="0" end="5" items="${posts}" var="post">
      <c:if test = "${!post.isArchived()}">
        <div class="post-item-wrapper">
          <div class="post-item">
            <div class="post-img" style="background-image: url(${post.getPhotoURL()})"></div>
            <div class="post-date">${post.getDating()}</div>
            <div class="post-content-wrapper">
              <span class="post-road">${post.getRoad().toString()}</span>
              <h3 class="post-title">${post.getTitle()}</h3>
              <p class="post-description">${post.getDescription()}</p>
              <div class="par-wrapper">
                <div class="post-author">
                  <c:choose>
                    <c:when test='${post.getAccount().getEmail().equals(user)}'>
                        <div class="pa-img"><i class="pa-img-i-x pa-img-i fa fa-user-circle-o" aria-hidden="true"></i></div>
                    </c:when>
                    <c:otherwise>
                        <div class="pa-img"><i class="pa-img-i fa fa-user-circle-o" aria-hidden="true"></i></div>
                    </c:otherwise>
                  </c:choose>
                  <span class="pa-name">${post.getAccount().getUsername()}</span>
                </div>
                <c:choose>
                  <c:when test='${post.getSupporters().contains(user)}'>
                    <div id="${post.getId()}" class="rc-img rc-img-active likes">
                  </c:when>
                  <c:otherwise>
                    <div id="${post.getId()}" class="rc-img likes">
                  </c:otherwise>
                </c:choose>
                  <span>${post.getSupport()}</span>
                  <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:if>
    </c:forEach>
  </c:if>
</div>