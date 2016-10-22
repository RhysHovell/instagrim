<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.Comments"%>
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
                
                     <a class="navbar-brand"href="Profile">Instagrim</a>
                 </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li><a href="/Instagrim/Profile">Profile</a></li>
                            <li><a href="/Instagrim/Upload">Upload</a></li>
                            <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                            <ul class="nav navbar-nav navbar-right">
                            <li><a href="/Instagrim/Logout">Logout</a></li>
                            </ul>
                    </div>
                        </ul>
            </div>
        </nav>
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            java.util.LinkedList<Comments> lsComments = (java.util.LinkedList<Comments>) request.getAttribute("Comments");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();
        %>
                    <h1>Your Pics</h1>
        <a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/>
        <form method ="POST" enctype="multipart/form-data" action="/Instagrim/Comment">
            <input type ="text" name="username" value="<%=lg.getUsername()%>" hidden>
            <input type ="text" name="picid" value="<%=p.getSUUID()%>" hidden>          
            <input type ="text" name="comment" value ="">
            <input type ="submit" value="Submit" id="comment">
        </form>

        <%
            if (lsComments == null) {
        } else {
            Iterator<Comments> itComment;
            itComment = lsComments.iterator();
            while (itComment.hasNext()) {
                Comments c = (Comments) itComment.next();
                
               if(c.getPicID().toString().equals(p.getSUUID())){
                

                    %>
                    
                    
                    <p>user:<%=c.getUser()%></p>
                    <p>comment<%=c.getComment()%></p>
                    <p>date<%=c.getDate()%></p>
                    
                    
                          <%
                }
}
}
}
}
%>

    </body>
</html>



