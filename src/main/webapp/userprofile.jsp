
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
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>User Profile</title>
    </head>
    <body>
         <%
         
         String username = null;
         
         LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
         username = lg.getUsername();
         
         ProfileBean profile = new ProfileBean();
         profile = (ProfileBean) request.getAttribute("Profile");
         
         if(profile != null)
         {
        %>
         <nav class ="navbar navbar-inverse">
            <div class="container-fluid">
                 <div class="navbar-header">
                     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                     </button>
                     <a class="navbar-brand">Instagrim</a>
                 </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li><a href="Profile">Profile</a></li>
                             <li><a href="Upload">Profile</a></li>
                            <li><a href="Images/<%=lg.getUsername()%>">Your Images</a></li>
                            <ul class="nav navbar-nav navbar-right">
                            <li><a href="Logout">Logout</a></li>
                            </ul>
                        </ul>
            </div>
        </nav>
        

    <ul>
        <li>Profile of:<%=username%> </li> 
        <li>First Name:<%=profile.getFirstName()%></li>
        <li>Surname:<%=profile.getLastName()%></li>
        <li>Email:<%=profile.getEmail()%></li>
        <%
            Pic p = (Pic) request.getAttribute("ProfilePic");
            if (p == null) {
        %>
        <img id="profilePicture" class="thumbnail" alt="User profile picture" src="/Instagrim/displayProfilePicture">
        <li><a href="EditProfile">Edit Profile</a></li>
       </ul>
        <%
        } else {
              
        %>
                   
       

            
            

        

        <%  
        }
}
        %>
    </body>
</html>
