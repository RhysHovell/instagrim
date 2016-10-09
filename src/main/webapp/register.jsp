<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="Styles.css">
    </head>
    <body>
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

                            <li><a href="Search"><span class="glyphicon glyphicon-search"></span></a></li>
                        </ul>
                            <ul class="nav navbar-nav navbar-right">
                            <li><a href="Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        </ul>
                            </div>

                 </div>

        </nav>
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>
                    <li>First Name <input type="text" name="first_name"></li>
                    <li>Last Name<input type="text" name="last_name"></li>
                    <li>Email<input type="text" name ="email"></li>
                </ul>
                <input type="submit" value="Register">
            </form> 
    </body>
</html>
