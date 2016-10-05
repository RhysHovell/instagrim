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
                     <a class="navbar-brand" href="index.jsp">Instagrim</a>
                 </div>
                     <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            
                            <li><a href="upload.jsp">Upload</a></li>
                            <%

                                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                                if (lg != null) {
                                    String UserName = lg.getUsername();
                                    if (lg.getLoggedin()) {
                            %>
                            
                            <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                            <li><a href="Logout">Logout</a></li>
                            <%}
                                    }else{
                                        %>
                            <li><a href="register.jsp">Register</a></li>
                        
                            
                            <li><a href="userprofile.jsp">Profile</a></li>
                            <li><a href="search.jsp"><span class="glyphicon glyphicon-search"></span></a></li>
                        </ul>
                            <ul class="nav navbar-nav navbar-right">
                            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
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
