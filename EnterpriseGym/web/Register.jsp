<%-- 
    Document   : about
    Created on : 15-Sep-2015, 13:04:49
    Author     : davidkenny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%@include file="header.jsp" %>
    
    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <h1>Register</h1>
                <p></p>
            </div>
        </div>
        
            <div class="container">   
                
            <form  action="SignUp" method="post" >
                Username: <br>
           <input name="username" type="text" id="userBox" maxlength="45"/>
           <br> Password: <br>
           <input name="password" type="password" id="userBox" maxlength="45"/> 
           <br> Password Check: <br>
           <input name="passwordcheck" type="password" id="userBox" maxlength="45" />
           <br> First name: <br>
           <input name="first" type="text" id="userBox" maxlength="45" />
           <br> Last name: <br>
           <input name="last" type="text" id="userBox" maxlength="45" />
           <br> Gender: <br>
           <input name="gender" type="text" id="userBox" maxlength="45" />
           <br> Country: <br>
           <input name="country" type="text" id="userBox" maxlength="45" />
           <br> University: <br>
           <input name="university" type="text" id="userBox" maxlength="45" />
           <br> School: <br>
           <input name="school" type="text" id="userBox" maxlength="45" />
           <br> Subject: <br>
           <input name="subject" type="text" id="userBox" maxlength="45" />
           <br> Country: <br>
           <input name="country" type="text" id="userBox" maxlength="45" />
           <br>
           <input type="submit" value="Sign In" />        
          </form> 
                
            </div>
        
        
        
    </div>
    
    <%@include file ="footer.jsp" %>
    </body>
</html>
