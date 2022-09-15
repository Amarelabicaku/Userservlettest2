<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/14/2022
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<html>
  <head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style type="text/css">
      <%@include file="/Css/post.css"%>
    </style>

      <title>Title</title>
  </head>
  <script>
    var email = '${loggedUser}';
    localStorage.setItem('email', email);
    function clearStorage(){
      localStorage.clear();
      window.location.href = "/my-first-application/showlogin";
    }
  </script>
  <body class="content">
  <h>Logged User</h>

  <%= request.getParameter("email")%>




    <h4 class="tittle">Posts by our users</h4>
</div>
    <div style="btn-float-left">
  <button type="submit"class="btn btn-primary1" onClick="clearStorage()">Log Out</button>
    </div>
  <div class="create-post" >

    <form class="form-create-post" action="do-post" method="post">
      <div class="card" style="width: 18rem;">
        <div class="card-body">

          <label class="form-control-label">What's on your mind ?</label>

            <input type="hidden" name="email" value="<c:out value='${loggedUser}' />" />

         <textarea name="message" class="form-control"></textarea>
          <div class="post-button">

            <button type="submit" class="btn btn-primary">Post</button>

          </div>
        </div>
      </div>
    </form>
  </div>
  <c:forEach var="post" items="${userDhePoste}">
            <div class="wall-posts">

          <div class="one-card">
           <div class="card">
             <form class="card-body">
               <h5 class="card-title" ><c:out value="${post.name} ${post.surname}"/></h5>
               <h6 class="card-subtitle"><c:out value="${post.date}"/></h6>
               <p class="card-text"><c:out value="${post.description}"/></p>
                 <p class="card-text"><c:out value="${post.postime_id}"/></p>
                 <a href="edit-post?postime_id=<c:out value='${post.postime_id}' />">Edit Post</a>
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     &nbsp;&nbsp;&nbsp;&nbsp;
                             <a href="delete-post?postime_id=<c:out value='${post.postime_id}' />">Delete Post</a>

             </form>
           </div>
         </div>

            </div>
  </c:forEach>

  </body>
</html>
