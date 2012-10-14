<%-- 
    Document   : LoginAttempt
    Created on : Sep 24, 2012, 6:20:49 AM
    Author     : Sweord
--%>

<%@page import="SQL.SQLServerLogin"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="SQL.ServerLoginDriver"%>
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

            SQLServerLogin sl = new SQLServerLogin();
            
            sl.ConnectToUserDB();
            
            if (sl.ValidateLogin(username, password) == SQLServerLogin.USERAUTHENTICATED) {
                %>Success!<%  
            } 
            else {
                %>Login failed!<%                    
            }
        %>
    </body>

</html>
