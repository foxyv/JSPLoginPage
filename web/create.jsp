<%-- 
    Document   : CreateUser
    Created on : Oct 13, 2012, 9:58:45 PM
    Author     : Sweord
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <div align="center">
            <h2>Create User</h2>
            <script type="text/javascript" src="spin.js"></script>
            <script type="text/javascript" src="submit.js"></script>

            Username: <input type="text" name="username" value=""  id="user" /><br>
            Password: <input type="password" name="password" value=""  id="pass" /><br><br>

            <input id="loginButton" type="button" value="Create Login" name="send" onclick="submitUser();" align="middle"/> <br><br>
            <p id="UserCreateStatus"></p>


            <p id="UserCreateResult"> </p>
        </div>
    </body>
</html>
