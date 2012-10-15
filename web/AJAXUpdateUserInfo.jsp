<%-- 
    Document   : AJAXUpdateUserInfo
    Created on : Oct 14, 2012, 10:06:18 PM
    Author     : Sweord
--%>

<%@page import="SQL.UserBean"%>
<%@page import="java.util.jar.Attributes.Name"%>
<%@page import="SQL.SQLServerLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:useBean id="loginInfo" scope="session" class="SQL.UserBean" />
        <jsp:useBean id="SQLConnection" scope="session" class="SQL.SQLServerLogin" />


        <%            
            SQLConnection.ConnectToUserDB();
            loginInfo.setUsername(request.getParameter("username") + "");
            loginInfo.setFirstname(request.getParameter("firstname") + "");
            loginInfo.setEmail(request.getParameter("email") + "");
            loginInfo.setOccupation(request.getParameter("occupation") + "");
            loginInfo.setAuthenticated("Authenticated" + "");
            
            try {
                int parsedAge = Integer.parseInt(request.getParameter("age"));
                loginInfo.setAge(parsedAge);
            } catch (Exception ex) {
                out.println(ex + "<br>");
            }
            
            UserBean aBean = loginInfo;
            
            
            
            if (SQLConnection.updateUserBean(aBean)) {
                
        %>Update Successful!<br><%                } else {
        %>Update Failed!<br><%                    }
            
            
        %>

        <jsp:setProperty name="loginInfo" property="firstname" value="<%=loginInfo.getFirstname()%>" />
        <jsp:setProperty name="loginInfo" property="age" value="<%=loginInfo.getAge()%>" />
        <jsp:setProperty name="loginInfo" property="email" value="<%=loginInfo.getEmail()%>" />
        <jsp:setProperty name="loginInfo" property="occupation" value="<%=loginInfo.getOccupation()%>" />
        <jsp:setProperty name="loginInfo" property="username" value="<%=loginInfo.getUsername()%>" />


    </body>
</html>
