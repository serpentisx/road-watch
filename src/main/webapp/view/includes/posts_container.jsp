
<div class="posts-container">
  <c:if test = "${posts != null}">
    <c:forEach items="${posts}" var="post">
      <c:if test = "${!post.isArchived()}">
        
        <div class="post-item-wrapper">
          <div class="post-item">
            
            <div class="post-img">
              <div class="heart-img">
                 <i class="fa fa-heart" aria-hidden="true"></i>
              </div>
            </div>
            
            <div class="post-content-wrapper">
              <h3 class="post-title"><c:out value = "${post.getTitle()}"/></h3>
              <p class="post-description"><c:out value = "Lýsing: ${post.getDescription()}"/></h3>
              <div class="post-author">
                <div class="pa-img"></div>
                <span class="pa-name"><c:out value = "Höfundur: ${post.getAccount().getUsername()}"/></span>
              </div>
              <p class="post-date"><c:out value = "${post.getDating()}"/></p>
              <p><c:out value = "(${post.getLatitude()}, ${post.getLongitude()})"/></p>
              <p><c:out value = "${post.getRoad().getName()}"/></p>
              <div class="line"></div>
              <div class="reaction-container">
                <div class="rc-img likes">
                   <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                   <span><c:out value = "${post.getSupport()}"/></span>
                </div>
                <!--
                <div class="rc-img dislikes">
                   <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                   <span>3</span>
                </div>
                <div class="rc-img comments">
                   <i class="fa fa-comment" aria-hidden="true"></i>
                   <span>5</span>
                </div>
                -->
              </div>
              <!--
              <figure>
                <img src="img/posts/${post.getPhotoURL()}" alt="Meðfylgjandi mynd">                         
              </figure>
              -->
            </div>            
          </div>
        </div>                  
      </c:if>
    </c:forEach>
  </c:if>
</div>