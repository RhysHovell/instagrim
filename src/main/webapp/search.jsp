<%-- 
    Document   : search
    Created on : 17-Sep-2016, 21:33:37
    Author     : Rhys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <script>
            function ajax(){
                var xhttp;
                if (window.XMLHttpRequest) {
                    xhttp = new XMLHttpRequest();
                } else {
                    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200){
                        document.getElementById("ajaxoutput").innerHTML = this.responseText;
                    }
                };
                xhttp.open("GET", "SearchAll", true);
                xhttp.send();
            }
            </script>
        
        <title>Search</title>
    </head>
    <body>
        <%
           String username = null;
           LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
           username = lg.getUsername(); 
        %>
           <nav class ="navbar navbar-inverse">
            <div class="container-fluid">
                 <div class="navbar-header">
                     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                     </button>
                     <a class="navbar-brand" href="Profile">Instagrim</a>
                 </div>
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
        </nav>
        <h1>Search For a User</h1>
        <form method ="POST" action="Search">
            <input id="searchUser" type="text" name="searchBox" class="inputBox">
            <input type ="submit" value="Search">
            
        </form>
        
        <h1>View All Users</h1>
        <a class="btn btn-default" onclick ="ajax();" role ="button">View data</a>
        <div id ="ajaxoutput"></div>
    </body>
</html>
