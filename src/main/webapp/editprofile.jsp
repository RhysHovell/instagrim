<%-- 
    Document   : editprofile
    Created on : 07-Oct-2016, 16:22:04
    Author     : rhysh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        
    </head>
    <body>

        <%
            String username;
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            username = lg.getUsername();

            ProfileBean profile = new ProfileBean();
            profile = (ProfileBean) request.getAttribute("Profile");
            
        %>
        
        <nav class ="navbar navbar-inverse">
            <div class="container-fluid">
                 <div class="navbar-header">
                     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                     </button>
                     <a class="navbar-brand"href="Profile">Instagrim</a>
                 </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li><a href="Profile">Profile</a></li>
                            <li><a href="Upload">Upload</a></li>
                            <li><a href="Images/<%=lg.getUsername()%>">Your Images</a></li>
                            <ul class="nav navbar-nav navbar-right">
                            <li><a href="Logout">Logout</a></li>
                            </ul>
                        </ul>
                    </div>
                   </div>
 
        </nav>

                            <h1>Edit your profile</h1>
                   <%
                       if (profile != null){
                            %>

                            <form method ="POST" action="EditProfile">  
                                <ul>
                                <li>Profile of:<%=username%></li>
                                <li>First Name:<input type="text"name="first_name" value = "<%=profile.getFirstName()%>"</li>
                                <li>Surname:<input type="text"name="last_name" value = "<%=profile.getLastName()%>"</li>
                                <li>Email:<input type="text"name="email" value = "<%=profile.getEmail()%>"</li>
                                </ul>
                                <input type ="submit" value="Update">
                                
                            </form>
                                <h1>Update Profile Picture</h1>
            <img id="profilePicture" class="thumbnail" alt="User profile picture" src="/Instagrim/displayProfilePicture">
           
            <form method="POST" enctype="multipart/form-data" action="setProfilePicture">
                File to upload: <input type="file" name="profilePic"><br/>
                
                <br/>
                <input type="submit" value="Press"> to upload the file!
              
            </form>
                                <% 
                                 }
                                %>
           

    </body>
</html>
