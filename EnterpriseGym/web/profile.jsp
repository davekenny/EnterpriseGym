<%-- 
    Document   : profile
    Created on : 17-Sep-2015, 10:48:10
    Author     : kristiyangeorgiev
--%>
<%@page import="java.util.Iterator"%>
<%@page import="Entities.UserEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link href='css/profile.css' rel='stylesheet' type='text/css'>

    </head>
    <body>
        <%@include file="header.jsp" %>
        

        <%
            java.util.LinkedList<UserEntity> user = null;
            user = (java.util.LinkedList<UserEntity>) request.getAttribute("userdetails");
            Iterator<UserEntity> iterator;
            if (user != null) {
                iterator = user.iterator();
        %>

        <!-- Page Content -->
        <div class="banner"></div>        
        <div class="container">

            <br><br>


          

            <div class="col-md-12" id="ConnectText">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-check"></i><strong>Details</strong></h4>
                    </div>
                    <div class="panel-body">
                        <table class="table table-hover">
                            <%
                                while (iterator.hasNext()) {
                                    UserEntity p = (UserEntity) iterator.next();
                            %>      
                            <thead>
                                <tr>
                                    <th>Firstname:</th>
                                    <th><%=p.getName()%></th>
                                </tr>
                                <tr>
                                    <th>LastName:</th>
                                    <th><%=p.getLastname()%></th>
                                </tr>
                                <tr>
                                    <th>Gender:</th>
                                    <th><%=p.getGender()%></th>
                                </tr>
                                <tr>
                                    <th>Country:</th>
                                    <th><%=p.getCountry()%></th>
                                </tr>
                                <tr>
                                <tr>
                                    <th>Email:</th>
                                    <th><%=p.getEmail()%></th>
                                </tr>
                            <th>University:</th>
                            <th><%=p.getUniversity()%></th>
                            </tr>
                            <tr>
                                <th>School:</th>
                                <th><%=p.getSchool()%></th>
                            </tr>
                            <tr>
                                <th>Year:</th>
                                <th><%=p.getYearOfStudy()%></th>
                            </tr>
                            <tr>
                                <th>Matriculation Number:</th>
                                <th><%=p.getMatric()%></th>
                            </tr>
                            </thead>
                            <%}
            }%>
                        </table>
                    </div>


                </div>
            </div>
        </div>




        <%@include file ="footer.jsp" %>
    </body>
</html>
