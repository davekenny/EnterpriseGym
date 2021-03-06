<%-- 
    Document   : newsadmin
    Created on : Sep 23, 2015, 1:01:40 PM
    Author     : davidkenny
--%>

<%@page import="java.util.Iterator"%>
<html>
    <link href='css/carousel.css' rel='stylesheet' type='text/css'>
    <link href='css/media.css' rel='stylesheet' type='text/css'>
    <%@include file="header.jsp" %>

 
    <script src="${pageContext.request.contextPath}/js/admin.js" type="text/javascript"></script>


    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 id="modaltitle" class="modal-title">Delete</h4>
                </div>
                <div class="modal-body">
                    <p id="modalmessage">Are you sure you want to delete this account?</p>

                </div>
                <div class="modal-footer">
                    <button id="yes" type="button" onclick="deleteNews()" class="btn btn-Danger" data-dismiss="modal">Yes</button>  
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="myModal2" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 id="modaltitle2" class="modal-title">Success</h4>
                </div>
                <div class="modal-body">
                    <p id="modalmessage">Account deleted.</p>

                </div>
                <div class="modal-footer">
                    <button type="button" onclick="reload()" class="btn btn-default" data-dismiss="modal">Ok</button>
                </div>
            </div>

        </div>
    </div>

    <div class="modal fade" id="myModal3" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 id="modaltitle3" class="modal-title">Success</h4>
                </div>
                <div class="modal-body">
                    <p id="modalmessage3">Message deleted.</p>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
                </div>
            </div>

        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1></h1>
                <p></p>
            </div>
        </div>

        <div class="col-md-12" id="ConnectText2">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4><i class="fa fa-fw fa-check"></i><strong>Account Admin Panel</strong></h4>
                </div>
                <div class="panel-body">
                    <table class="table table-hover" id="datatable2">
                        <%
                            java.util.LinkedList<Account> accountList = (java.util.LinkedList<Account>) request.getAttribute("accountList");
                            if (accountList.size() == 0) {
                        %>
                        <p>No News found.</p>
                        <%
                        } else { %>
                        <thead>
                            <tr>
                                <th>UserName</th>
                                <th>Date Joined</th>
                                <th></th>
                                <th></th>  
                                <th>Temp Account</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Iterator<Account> iterator;
                                iterator = accountList.iterator();
                                while (iterator.hasNext()) {
                                    Account nextAccount = (Account) iterator.next();
                            %>       
                            <tr>
                                <td><%=nextAccount.getUsername()%></td>
                                <td><%=nextAccount.getDateJoined()%></td>
                                <td><a role="button" href="../EditProfile/<%=nextAccount.getUsername()%>" class="btn btn-info">Edit <span class="glyphicon glyphicon-cog" aria-hidden="true"></span></button></td>
                                <%if (account.hasAccessLevel(9)) {%>
                                <td><a role="button" onclick="checkDelete(<%=nextAccount.getId()%>, '<%=nextAccount.getUsername()%>')" class="btn btn-Danger">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></td>              
                                <%
                                        }%>
                                <td><%if (nextAccount.isTemp()){%> <span class="glyphicon glyphicon-ok" aria-hidden="true"> <% }else{ %> <span class="glyphicon glyphicon-remove" aria-hidden="true"> <%}%></td>
                                    <%}
                                %></tbody> 
                            <%
                                }
                            %>
                    </table>
                    <a href="javascript:history.back()" class="btn btn-danger">Back</a>
                </div>
            </div>
        </div>  
    </div>
    <%@include file ="footer.jsp" %>
</body>
</html>