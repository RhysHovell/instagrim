<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" >
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        
 <%
            String username = null;
            
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
                
                     <a class="navbar-brand" href="index.jsp">Instagrim</a>
                 </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li><a href="/Instagrim/Profile">Profile</a></li>
                            <li><a href="/Instagrim/upload.jsp">Upload</a></li>
                            <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                            <ul class="nav navbar-nav navbar-right">
                            <li><a href="/Instagrim/Logout">Logout</a></li>
                            </ul>
                    </div>
                        </ul>
            </div>
        </nav>
        <%  
            if(lg.getLoggedin() == true){
               String UUID = (String) request.getAttribute("Pic");
            }
            java.util.LinkedList<Comments> lsComments = (java.util.LinkedList<Comments>) request.getAttribute("Comments");
            if (lsComments == null) {
        %>
        <p>No Comments found</p>
        <%
        } else {
            Iterator<Comments> iterator;
            iterator = lsComments.iterator();
            while (iterator.hasNext()) {
                Comments c = (Comments) iterator.next();
                String user = c.getUser();
                String comment = c.getComment();
                Date date = c.getDate();
                

        %>
        <p>user:<%=user%></p>
        <p>comment:<%=comment%></p>
        <p>date:<%=date%>></p>
        <% }
}
            %>
        </article>
    </body>
</html>
