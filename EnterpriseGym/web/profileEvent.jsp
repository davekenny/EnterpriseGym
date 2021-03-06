<%@page import="Entities.EventUserEntity"%>
<%@page import="Entities.EventEntity"%>
<%@page import="java.util.Iterator"%>
<%@page import="Entities.UserEntity"%>
<html>

    <%@include file="header.jsp" %>
    




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
                    <h4><i class="fa fa-fw fa-check"></i><strong>My Events</strong></h4><br>
                </div>
                <div class="panel-body">
                    <%
                        java.util.LinkedList<EventUserEntity> event = (java.util.LinkedList<EventUserEntity>) request.getAttribute("eventdetails");
                        if (event.size() == 0) {
                    %>
                    <p>No Events Found.</p>
                    <%
                    } else { %>
                    <table class="table table-hover" id="datatable2">
                        <thead>
                            <tr>
                                <th>Event Name</th>
                                <th>View Event</th>                               
                                <th>Attended</th>  
                                <th>Points</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%

                                Iterator<EventUserEntity> iterator;
                                iterator = event.iterator();
                                
                                while (iterator.hasNext()) {
                                    EventUserEntity p = (EventUserEntity) iterator.next();
                            %>       

                            <tr>
                                <td><%=p.getEventTitle()%></td>
                                <td><a role="button" href="${pageContext.request.contextPath}/Events/Event/<%=p.getEventid()%>" class="btn btn-primary">View <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button></td>
                                <td><%if (p.isAttended()){%> <span class="glyphicon glyphicon-ok" aria-hidden="true"> <% }else{ %> <span class="glyphicon glyphicon-remove" aria-hidden="true"> <%}%></td>
                                <td><%=p.getEventpoints()%></td>
                            <%  } %></tbody> <%}%>
                    </table>
                    <a href="javascript:history.back()" class="btn btn-danger">Back</a>
                </div>
            </div>
        </div>  
    </div>








    <%@include file ="footer.jsp" %>
</body>
</html>
