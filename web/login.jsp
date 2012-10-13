<%-- 
    Document   : login
    Created on : Sep 26, 2012, 6:20:58 PM
    Author     : Sweord
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <script type="text/javascript" src="spin.js"></script>
        <script type="text/javascript" src="submitLogin.js"></script>
        
        Username: <input type="text" name="username" value=""  id="user" /><br>
        Password: <input type="password" name="password" value=""  id="pass" /><br>
        <input id="loginButton" type="button" value="Send Login" name="send" onclick="submitLogin(); startSpinner();" align="middle"/> 
        <p id="LoginStatus"></p>
                
                
        <p id="LoginResult"> </p>
    </body>
</html>
