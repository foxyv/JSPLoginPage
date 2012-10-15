<%-- 
    Document   : UserInfo
    Created on : Oct 14, 2012, 5:52:37 PM
    Author     : Sweord
--%>

<%@page import="javax.swing.text.Document"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Info Page</title>
    </head>
    <body>
        <script type="text/javascript" src="spin.js"></script>
        <script type="text/javascript" src="submit.js"></script>
        <jsp:useBean id="loginInfo" scope="session" class="SQL.UserBean" />
        <jsp:useBean id="SQLLogin" scope="session" class="SQL.SQLServerLogin" />

        
        <h1>Hello <%=loginInfo.getUsername()%>!</h1>
        
        First Name: <input type="text" name="firstname" value="<%= loginInfo.getFirstname()%>" id="firstname" /><br>
        Age: <input type="text" name="age" value="<%= loginInfo.getAge()%>" id="age" /><br>
        Email: <input type="text" name="email" value="<%= loginInfo.getEmail()%>" id="email" /><br>
        Occupation: <input type="text" name="occupation" value="<%= loginInfo.getOccupation()%>" id="occupation" /><br><br>
        <input type="button" value="Update User Info" name="update" onclick="userInfoForm('<%= loginInfo.getUsername() %>')" /><br><br>
        <p id="spinner"></p>
        <p id="SubmitResult"></p>
        <p id="javascript"></p>



    </body>
</html>
