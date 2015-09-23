<!DOCTYPE html>
<html lang="en">

    <%@include file="header.jsp" %>
    <script src="${pageContext.request.contextPath}/js/stats.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="http://code.highcharts.com/modules/exporting.js"></script>
    <script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/charts/userchart.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/charts/eventchart.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/charts/eventuserchart.js" type="text/javascript"></script>
    <div class="hidden-xs">
        <%@include file="sidebar.jsp"%>
    </div>

    <!-- Page Content -->
    <div class="content">
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <h1>Stats & Search</h1>
                    <p></p>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-check"></i>Search</h4>
                        <form id="searchFor">
                            <input type="radio" id="userRadio" name="search" value="Users" checked="checked">  Users
                            <input style="margin-left: 20px;" type="radio" name="search" value="Events">  Events<br></br>
                            <div class="input-group">
                                <input name="username" type="text" class="form-control" id="searchValue" maxlength="45"/>
                                <span class="input-group-btn">
                                    <input style="margin-left: 10px;" type="submit" value="Search" class="btn btn-default">
                                </span>
                            </div>
                        </form>
                    </div>
                        <div id="tableBody" class="panel-body">
                            <table id="dataTable" class="display" cellspacing="0" width="100%">
                                <thead>
                                    <tr id="tableHeader">
                                    </tr>
                                </thead>

                                <tfoot id="tableFooter">
                                    <tr>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4><i class="fa fa-fw fa-check"></i>Users</h4>
                                <label>Property</label>
                                <select id="userproperty">
                                    <option selected="selected"></option>
                                </select>
                            </div>
                            <div class="panel-body">
                                <div id="userchart" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4><i class="fa fa-fw fa-check"></i>Events</h4>
                                <label>Property</label>
                                <select id="eventproperty">
                                    <option selected="selected"></option>
                                </select>
                            </div>
                            <div class="panel-body">
                                <div id="eventchart" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4><i class="fa fa-fw fa-check"></i>Users vs Events</h4>
                                <label>Filter by user:</label>
                                <select id="userbarproperty">
                                    <option selected="selected"></option>
                                </select>
                                <label style="margin-left:30px;">Value:</label>
                                <select id="valuebarproperty">
                                    <option selected="selected"></option>
                                </select>
                            </div>
                            <div class="panel-body">
                                <div id="eventuserchart" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                            </div>
                        </div>
                        <hr>
                    </div>
                </div>

                <%@include file ="footer.jsp" %>

                </body>

                </html>
