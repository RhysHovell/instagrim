
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
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>User Profile</title>
    </head>
    <body>
        <h1>Welcome to Your Profile!</h1>
         <%
         
         String username = null;
         
         LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
         username = lg.getUsername();
         
         ProfileBean profile = new ProfileBean();
         profile = (ProfileBean) request.getAttribute("Profile");
         
         if(profile != null)
         {
        %>
        <li><a href="EditProfile">Edit Profile</a></li>

     
        <h2>Profile of:<%=username%> </h2> 
        <p>First Name:<%=profile.getFirstName()%></p>
        <p>Surname:<%=profile.getLastName()%></p>
        <p>Email:<%=profile.getEmail()%></p>
        <%  }
        
        %>
    </body>
</html>
