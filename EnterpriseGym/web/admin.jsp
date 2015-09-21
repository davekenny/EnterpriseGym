<%-- 
    Document   : admin
    Created on : 21-Sep-2015, 16:44:50
    Author     : Andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <link href='css/style.css' rel='stylesheet' type='text/css'>
    </head> 
   
    <body>
    <%@include file="header.jsp" %>
    <div class="hidden-xs">
        <%@include file="sidebar.jsp"%>
    </div>
    
    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <h1>Admin Panel</h1>
                <p></p>
            </div>
        </div>
        
        <div class="col-md-12" id="newsPanel">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><i class="fa fa-fw fa-check"></i>News Editor</h4>
                </div>
                <div id="newsEditor" class="panel-body" hidden>

                </div>
            </div>
        </div>
        <script>
            $("#newsPanel").click(function() {
                $("#newsEditor").toggle( "fast", function() {
                  // Animation complete.
                });
            });
        </script>
        <div class="col-md-12" id="userPanel">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><i class="fa fa-fw fa-check"></i>User Management</h4>
                </div>
                <div id="userEditor" class="panel-body" hidden>

                </div>
            </div>
        </div>
        <script>
            $("#userPanel").click(function() {
                $("#userEditor").toggle( "fast", function() {
                  // Animation complete.
                });
            });
        </script>
        <div class="col-md-12" id="eventsPanel">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><i class="fa fa-fw fa-check"></i>Events Management</h4>
                </div>
                <div id="eventsEditor" class="panel-body" hidden>

                </div>
            </div>
        </div>
        <script>
            $("#eventsPanel").click(function() {
                $("#eventsEditor").toggle( "fast", function() {
                  // Animation complete.
                });
            });
        </script>
        <div class="col-md-12" id="quizPanel">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><i class="fa fa-fw fa-check"></i>Quiz Management</h4>
                </div>
                <div id="quizEditor" class="panel-body" hidden>

                </div>
            </div>
        </div>
        <script>
            $("#quizPanel").click(function() {
                $("#quizEditor").toggle( "fast", function() {
                  // Animation complete.
                });
            });
        </script>
    </div>
    
    <%@include file ="footer.jsp" %>
    </body>
</html>

