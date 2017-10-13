<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="javax.servlet.http.HttpServlet" %>
<%@page import="java.io.File" %>
<%@page import="mm.com.aidatech.utilities.www.PhotoProcessing" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Profile</title>
        <script src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script src="SpryAssets/GeneralPopUpForm.js"></script>
        <script>
            $(function(){
                $("#header").load("header.jsp");
                $("#footer").load("footer.html");
            });
        </script>
        <link rel="shortcut icon" type="image/x-icon" href="bootstrap-3.3.5-dist/MyLogo.png" />
        <link href="SpryAssets/GeneralForm.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div id="header"></div>

        <div id="main" style="background-image:url(data0/images/snow.png)">
            <table width="538" height="391" cellpadding="1" cellspacing="1">
                <tr>
                    <td width="264" rowspan="3" align="center">
                        <%
        String picName = request.getSession().getAttribute("UserId").toString() + ".jpg";
        boolean foundImage = new PhotoProcessing().findPicture(getServletContext(), "profile", picName);
        out.println("<img src=profile/" + ((foundImage) ? picName : "0.jpg") + " width=\"247\" height=\"198\" alt=\"" +
                request.getSession().getAttribute("UserName") + "\" /><br/>");
                        %>

                        <form method="post" action="UploadServlet" enctype="multipart/form-data">
                            Change Profile:
                            <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
                            <input type="submit" value="Upload" align="middle" />                            
                        </form>

                    </td>
                    <td width="265" height="85" align="left">Name: ${UserName}</td>
                </tr>
                <tr>
                    <td height="94" align="left">Email: ${Email}</td>
                </tr>
                <tr>
                    <td height="123" align="left">Phone: ${Phone}</td>
                </tr>
                <tr>
                    <td align="center">
                        <form action="profile" method="post">
                            <c:choose>
                                <c:when test="${ActivationStatus==1}">
                                    <input type="submit" name="btnActivation" id="btnActivation" value="DeActivate" align="middle" style="background-color:red;"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" name="btnActivation" id="btnActivation" value="ReActivate" align="middle"/>

                                </c:otherwise>
                            </c:choose>
                        </form>

                    </td>
                    <td align="left">Birthday: ${DOB}</td>
                </tr>
            </table>
        </div>

        <div id="footer"></div>
    </body>
</html>