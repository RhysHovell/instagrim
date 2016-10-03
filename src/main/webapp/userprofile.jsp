
<%-- 
    Document   : userprofile
    Created on : 26-Sep-2016, 14:08:21
    Author     : Rhys
--%>

<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@page import="java.util.*"%>
 
 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
    </head>
    <body>
        <h1>Welcome to Your Profile!</h1>
         <%
         String username = null;
         
         LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn");
         username = lg.getUsername();
         
         ProfileBean profile = new ProfileBean();
         profile = (ProfileBean) request.getAttribute("ProfileBean");
         
        %>

     
         <h2>Profile of: <%=username%></h2> 
        <p>First Name:<%=profile.getFirstname()%></p>
       
    </body>
</html>
