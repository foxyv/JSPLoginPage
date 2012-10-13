<%-- 
    Document   : LoginAttempt
    Created on : Sep 24, 2012, 6:20:49 AM
    Author     : Sweord
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logging in!</title>
    </head>
    <body>
        <%
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
        %>
        <% out.println(username); %>
        <% out.println(password); %>
    </body>
    
</html>
