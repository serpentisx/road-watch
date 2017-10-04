<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<div class="posts-container">
  <c:if test = "${posts != null}">
    <c:forEach items="${posts}" var="post">
      <c:if test = "${!post.isArchived()}">
        
        <div class="post-item-wrapper">
          <div class="post-item">
            
            <div class="post-img" style="background-image: url(${post.getPhotoURL()})">
              <div class="heart-img">
                 <i class="fa fa-heart" aria-hidden="true"></i>
              </div>
            </div>
            
            <div class="post-content-wrapper">
              <span class="post-road">${post.getRoad().getName()}</span>
              <h3 class="post-title"><c:out value = "${post.getTitle()}"/></h3>
              <p class="post-description"><c:out value = "Lýsing: ${post.getDescription()}"/></h3>
              <div class="post-author">
                <div class="pa-img"></div>
                <span class="pa-name"><c:out value = "${post.getAccount().getUsername()}"/></span>
              </div>
              <p class="post-date"><c:out value = "${post.getDating()}"/></p>
              <div class="line"></div>
              <div class="reaction-container">
                <div class="rc-img likes">
                   <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                   <span><c:out value = "${post.getSupport()}"/></span>
                </div>
                <div class="rc-img dislikes">
                   <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                   <span>0</span>
                </div>
                <div class="rc-img comments">
                   <i class="fa fa-comment" aria-hidden="true"></i>
                   <span>0</span>
                </div>
              </div>
            </div>            
          </div>
        </div>                  
      </c:if>
    </c:forEach>
  </c:if>
</div>