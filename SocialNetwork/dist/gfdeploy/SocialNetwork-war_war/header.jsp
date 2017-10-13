<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <style type="text/css">
            body,td,th {
                color: #000;
            }
            body {
                background-color: #FFF;
            }
        </style>
        <script src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>	
        <link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
        <link rel="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">

    </head>

    <body>

        <table width="100%" cellspacing="1" cellpadding="1">
            <tr>
                <th width="18%" scope="col"><img src="bootstrap-3.3.5-dist/MyLogo.png" width="98" height="87" alt="MyLogo" longdesc="index.jsp" /></th>
                <th width="43%" align="left" valign="middle"><p><strong><em>Social Network </em></strong><em><strong>backend System</strong></em><strong></strong></p></th>

                <th width="39%" align="left" scope="col">

                    <c:choose>
                        <c:when test="${empty UserName}">

                            <a href="register.jsp" align="right" style="visibility:visible;"><span class="glyphicon glyphicon-user"></span>&nbspSign Up</a>&nbsp
                            <a href="profile.jsp" align="right" style="visibility:hidden;"><span class="glyphicon glyphicon-user"></span>${UserName}</a>
                            <a href="login.jsp" align="right" style="visibility:visible;"><span class="glyphicon glyphicon-log-in"></span>&nbsp Login</a>
                            <a href="logout" align="right" style="visibility:hidden;"><span class="glyphicon glyphicon-log-in"></span>Sign Out</a>
                        </c:when>
                        <c:otherwise>

                            <a href="login.jsp" align="right" style="visibility:hidden;">Login</a>
                            <a href="register.jsp" align="right" style="visibility:hidden;">Register</a>
                            <a href="profile.jsp" align="right" style="visibility:visible;"><span class="glyphicon glyphicon-user"></span>&nbsp${UserName}</a>&nbsp
                            <a href="logout" align="right" style="visibility:visible;"><span class="glyphicon glyphicon-log-in"></span>&nbspSign Out</a>
                        </c:otherwise>
                    </c:choose>
                </th>
            </tr>
        </table>
        <header>
            <div class="navbar navbar-inverse" style="z-index:4;">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="index.jsp" class="navbar-brand">Campus<sub> Network</sub></a>
                    </div>
                    <div class="collpase navbar-collapse" id="example">

                        <ul class="nav navbar-nav">
                            <li><a href="status.jsp">Status</a></li>
                            <li><a href="help.jsp">Help</a></li>
                            <li><a href="about.html">About</a></li>

                        </ul>
                    </div>
                </div>
            </div>
        </header>

    </body>
</html>