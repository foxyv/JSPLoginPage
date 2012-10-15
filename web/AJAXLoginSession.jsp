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
        <jsp:useBean id="loginInfo" scope="session" class="SQL.UserBean" />
        <jsp:setProperty name="loginInfo" property="*"/>
        <%
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            SQLServerLogin sl = new SQLServerLogin();
            
            sl.ConnectToUserDB();
            loginInfo = sl.makeAuthenticatedUserBean(username, password);
            if (loginInfo.getAuthenticated().equals("Authenticated")) {
                
                %>Success!
                <a href="UserInfo.jsp">View User Information</a>
                <%  
            } 
            else {
                %>Login failed!<%                    
            }
            
        %>
        
        <jsp:setProperty name="loginInfo" property="firstname" value="<%=loginInfo.getFirstname()%>" />
        <jsp:setProperty name="loginInfo" property="age" value="<%=loginInfo.getAge()%>" />
        <jsp:setProperty name="loginInfo" property="email" value="<%=loginInfo.getEmail()%>" />
        <jsp:setProperty name="loginInfo" property="occupation" value="<%=loginInfo.getOccupation()%>" />
        <jsp:setProperty name="loginInfo" property="username" value="<%=loginInfo.getUsername()%>" />
        
    </body>

</html>
