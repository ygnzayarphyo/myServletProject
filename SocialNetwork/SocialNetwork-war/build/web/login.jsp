<%--
   - Author: @Zayar
   - Date: Oct 10, 2017 1:20:51 AM
   - Description: This jsp file is about login process for unauthorized person to access full features of the system.
   --%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Login</title>
        <script src="SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
        <script src="SpryAssets/SpryValidationPassword.js" type="text/javascript"></script>
        <link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
        <link href="SpryAssets/SpryValidationPassword.css" rel="stylesheet" type="text/css" />
        <link href="SpryAssets/GeneralForm.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" type="image/x-icon" href="bootstrap-3.3.5-dist/MyLogo.png" />
        <script type="text/javascript" src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#header").load("header.jsp");
                $("#footer").load("footer.html");
            });
        </script>

    </head>

    <body>

        <div id="header"></div>
        <div id="main" style="background-image:url(data0/images/snow.png)">
            <form action="LoginServlet" method="post" name="frmLogin">
                <table height="300" cellpadding="1" cellspacing="3">
                    <tr>
                        <td colspan="2"><h3>Login Form</h3></td>
                    </tr>
                    <tr>
                        <td valign="top" align="right" width="50%">Email: &nbsp;&nbsp;&nbsp;</td>
                        <td valign="top" align="left"><span id="sprytextfield1">
                                <input name="txtEmail" type="text" valign="top" id="txtEmail" size="25" maxlength="49" placeholder="example@gmail.com" />
                        <span class="textfieldRequiredMsg"><br/>A value is required.</span></span></td>
                    </tr>
                    <tr>
                        <td valign="top"  align="right" width="50%">Password: &nbsp;&nbsp;&nbsp;</td>
                        <td cellspacing="2" valign="top" align="left"><span id="sprypassword1" >
                                <input name="txtPassword" type="password" id="txtPassword" size="25" maxlength="29" placeholder=">=8 characters"/>
                        <span class="passwordRequiredMsg"><br/>A value is required.</span></span></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right"><input name="btnLogin"  type="submit" id="btnLogin" value="Login" size="20" accesskey="L" /></td>
                    </tr>
                </table>
                <input type="hidden" name="returnType" value="form" />
            </form>
        </div>

        <div id="footer"></div>

        <script type="text/javascript">
            var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1");
            var sprypassword1 = new Spry.Widget.ValidationPassword("sprypassword1");
        </script>
    </body>
</html>