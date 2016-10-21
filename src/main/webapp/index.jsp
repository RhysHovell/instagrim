<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>

<html>
    <head>
     
        
      
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="Styles.css" >
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <style>
    /* Remove the navbar's default margin-bottom and rounded borders */
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
      background-color: #125688;
    }
    .li {
      color: whitesmoke;
        
    }
    </style>
        <title>Instagrim</title>
    </head>
    <body>
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
                            
                           
                            <%


                                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                                if (lg != null) {
                                    String username = lg.getUsername();
                                    if (lg.getLoggedin()) {
                            %>
                            <li><a href="Profile">Profile</a></li>
                            <li><a href="Images/<%=lg.getUsername()%>">Your Images</a></li>
                            <li><a href="Logout">Logout</a></li>
                            <%}
                                    }else{
                                        %>
                            
                            <li><a href="Search"><span class="glyphicon glyphicon-search"></span></a></li>
                        </ul>
                            <ul class="nav navbar-nav navbar-right">
                            <li><a href="Register">Register</a></li>
                            <li><a href="Login"><span class="glyphicon glyphicon-log-in"></span>    Login</a></li>
                        </ul>
                            </div>
                            <%
                            }%>
                 </div>
            </div>

        </nav>
        <footer>
        </footer>
    </body>
</html>
