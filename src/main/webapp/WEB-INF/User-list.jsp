<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

</head>

<html>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #479dff">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> User
                Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<body>
<br>

<div class="row"><!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Registration
                </a>
            <a href="<%=request.getContextPath()%>/showlogin" class="btn btn-success">Login</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
<!--                <th>Password</th>-->
                <th>Address</th>
                <th>Surname</th>
                <th>Actions</th></tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${listUser}">

                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.country}"/></td>
<%--                    <td><c:out value="${user.password}"/></td>--%>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.surname}"/></td>
                    <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                </tr>
                </c:forEach>
                <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>