<%-- 
    Document   : CreateUserEntry
    Created on : Oct 14, 2012, 12:32:55 AM
    Author     : Sweord
--%>

<%@page import="SQL.SQLServerLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creating User!</title>
    </head>
    <body>
        <%

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            SQLServerLogin sl = new SQLServerLogin();
            
            sl.ConnectToUserDB();
            
            if (sl.CreateUser(username, password) == SQLServerLogin.USERCREATED) {
        %>Success! <a href="index.jsp">Return to index!</a><%  
            } 
            else {
                %>Login failed!<%                    
            }
        %>
    </body>
</html>
