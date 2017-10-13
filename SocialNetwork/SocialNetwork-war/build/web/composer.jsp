<%--
   - Author: @Zayar
   - Date: Oct 10, 2017 1:20:51 AM
   - Description: This program is about implementation of status Uploading Service.
   --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Composer</title>
        <link href="SpryAssets/GeneralForm.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="SpryAssets/SpryValidationTextField.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#header").load("header.jsp");
                $("#footer").load("footer.html");
            });
        </script>
        <link rel="shortcut icon" type="image/x-icon" href="bootstrap-3.3.5-dist/MyLogo.png" />
        <link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <%
        String requestAccess = request.getParameter("type"); // default type value must be api
        if (requestAccess == null) {
            /* when api call this frame, header must omitted..
             * When usual built-in web page call this page, header will be showed.
             */
            out.println("<div id=\"header\"></div>");
        } else {
            request.getSession().setAttribute("UserId", request.getParameter("userid").toString());
        }
        %>
        <div id="main">
            <c:choose>
                <c:when test="${empty UserId}">
                    <h3>You need login to share status.</h3>
                    <p>Go to <a href="login.jsp">Login</a> Page</p>
                </c:when>
                <c:otherwise>
                    <form action="StatusUpload" method="post"  name="frmComposer" id="frmComposer" enctype="multipart/form-data">
                        <table width="100%" height="367" cellpadding="5" cellspacing="5">
                            <tr>
                                <td width="30%" height="39"><input type="submit" src="status.jsp" name="btnCancel" id="btnCancel" value="Cancel" style="background-color:red;" /></td>
                                <td width="40%"><input name="image" type="file" /></td>

                                <td width="30%" align="right"><input type="submit" name="btnPost" id="btnPost" value="Post" /></td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center" width="100%">
                                    <span id="sprytextfield1">
                                        <br/>
                                        <input name="txtTitle" type="text" id="txtTitle" size="25" maxlength="34" />
                                <span class="textfieldRequiredMsg">A value is required.</span></span></td>
                            </tr>
                            <tr>
                                <br/>
                                <td colspan="3" align="center" width="100%"><textarea name="txtMessage" id="txtMessage" cols="90" rows="15" placeholder="What you want to share?" placeholder="What you want to share?"></textarea></td>
                                <br/>
                            </tr>
                            <input type="hidden" name="page" value="status" />
                        </table>
                    </form>
                </c:otherwise>
            </c:choose>

        </div>

        <div id="footer">

        </div>
        <script type="text/javascript">
            var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "none", {hint:"Title."});
        </script>
    </body>
</html>