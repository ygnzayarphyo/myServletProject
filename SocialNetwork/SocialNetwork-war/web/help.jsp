<%--
   - Author: @Zayar
   - Date: Oct 10, 2017 1:20:51 AM
   - Description: This is about help section including api documentation and administrators guide.
   --%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Help</title>
        <link rel="shortcut icon" type="image/x-icon" href="bootstrap-3.3.5-dist/MyLogo.png" />
        <link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css" />
        <link rel="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" />
        <style>
            body{
                padding: 10px 15px 15px 10px;
            }
        </style>

        <script type="text/javascript" src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#api").load("api.html");                
            });
        </script>
    </head>

    <body>

        <ul class="nav nav-pills navigation">
            <li><a href="index.jsp">Home</a></li>
            <li><a data-toggle="pill" href="#Tab1">API</a></li>
            <li><a data-toggle="pill" href="#Tab2">Documentation</a></li>            
            <li class="active"><a data-toggle="pill" href="#Tab4">Help</a></li>
        </ul>
        <div class="tab-content">

            <div class="tab-pane fade" id="Tab1">
                <div id="api"></div>
            </div>

            <div class="tab-pane fade" id="Tab2">
                The documentation about JavaDoc is under SocialNetwork -> SocialNetwork-war -> dist -> javadoc -> index.html.
            </div>
          <div class="tab-pane fade in active" id="Tab4">
            <br/>
              <p>&nbsp;</p>
              <p>Welcome!!!</p>
              <p>&nbsp;</p>
<p>Every user must be login and if they have no account, user can register to get an account. If user register successful to the system, they need to login again to access full feature of the system. If user want to de-activate the account, just go under <strong>profile&gt;deactive account</strong> and re activation process is vice visa. The post submision and comment section can be done when user successfully login to the system.</p>
              <p>If user is an administrator, then setup the environment like database tables please import <strong>social.sql </strong>file in <strong>WEB-INF</strong> to the MySQL database. For Database configuration, administrator must set data like database url, database username and password in <strong>db.conf </strong>file under <strong>WEB-INF</strong> by manually.</p>
              <p>For API usage, creator actually does not want to give PUT api because of security hole for the system, but to show  strength, creator opened register API for the system. The status and comment section api are focus for futher analytics processes based on user behaviors. </p>
              <p>&nbsp;</p>
              <p>By Zayar Phyo.</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
            </div>
        </div>

        <br/>
        <br/>
        <br/>

    </body>
</html>