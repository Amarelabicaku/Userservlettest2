<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/12/2022
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>User login </title>
    <style type="text/css">
      <%@include file="/Css/login.css"%>
    </style>
  </head>
  <body class="login">
  <h1>User Login</h1>
  <form action = "login" method="post">
<div>Enter your email: </div>
    <input type="text" name="email"/>
    <br/><br/>
    <div>Enter your password:</div>
     <input type="password" name="password"/>
    <br/>
    <br/>
                  <input type="submit" value="Login"/>
    <input type="reset" value="Reset ">

  </form>
  </body>
</html>
