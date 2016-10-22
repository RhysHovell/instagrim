<%-- 
    Document   : search
    Created on : 17-Sep-2016, 21:33:37
    Author     : Rhys
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <title>Search</title>
    </head>
    <body>
        <h1>Search For a User</h1>
        
        <a class="btn btn-default" onclick ="ajax();" role ="button">View data &raquo;</a>
        <div id ="ajaxoutput"></div>
        <h1>View All Users</h1>
    </body>
</html>
