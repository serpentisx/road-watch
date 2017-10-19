<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<div class="posts-container">
  <c:if test = "${posts != null}">
    <c:forEach begin="1" end="6" items="${posts}" var="post">
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
                  <div class="pa-img"></div>
                  <span class="pa-name">${post.getAccount().getUsername()}</span>
                </div>
                <div class="rc-img likes">
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