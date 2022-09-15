<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/19/2022
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update post</title>
</head>

<body>
<header>
    <a href="<%=request.getContextPath()%>/list-post"></a>
</header>
<form action="update-post" method="post">
    <div>
        <input type="hidden" name="postime_id" value="<c:out value='${post.postime_id}' />"/>
        <label>Edit Post </label>
        <input type="text"
               value="<c:out value='${post.description}' />"
               name="description" required="required">
        <button type="submit" class="btn btn-success1">Save Post</button>
    </div>
</form>
</body>
</html>
