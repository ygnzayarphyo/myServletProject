<%--
   - Author: @Zayar
   - Date: Oct 10, 2017 1:20:51 AM
   - Description: This section is about Creating new account for the user to access this system.
   --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <script src="SpryAssets/SpryValidationPassword.js" type="text/javascript"></script>
        <script src="SpryAssets/SpryValidationConfirm.js" type="text/javascript"></script>
        <script src="SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
        <link href="SpryAssets/SpryValidationPassword.css" rel="stylesheet" type="text/css">
        <link href="SpryAssets/SpryValidationConfirm.css" rel="stylesheet" type="text/css">
        <link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
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
            <form name="frmRegister" method="post" action="RegisterServlet">
                <table width="470" height="400" align="center"  cellpadding="1" cellspacing="2">
                    <tr>
                        <td colspan="3" align="center"><h3>Registration Form</h3></td>
                    </tr>
                    <tr>
                        <td width="152" align="right" valign="top" scope="col">User Name:</td>
                        <td colspan="2"  align="left" valign="top" scope="col"><span id="sprytextfield1">
                                <input name="txtUserName" type="text" id="txtUserName" size="33" maxlength="59">
                        <span class="textfieldRequiredMsg"><br/>A value is required.</span></span></td>
                    </tr>
                    <tr>
                        <td align="right" valign="top">Email:</td>
                        <td colspan="2"  align="left" valign="top"><span id="sprytextfield5">
                                <input name="txtEmail" type="text" id="txtEmail" size="33" maxlength="49">
                                <br>
                        <span class="textfieldRequiredMsg">A value is required.</span><span class="textfieldInvalidFormatMsg">Invalid format.</span></span><span class="textfieldRequiredMsg">A value is required.</span></td>
                    </tr>
                    <tr>
                        <td align="right" valign="top">Phone:
                        </td>
                        <td width="103" align="left" valign="top">
                            <select name="select">
                                <option value="+95" selected>Myanmar</option>
                                <option value="+65">Singapore</option>
                            </select><br>

                        </td>
                        <td width="199"  align="left" valign="top"><span id="sprytextfield3">
                        <input name="txtPhoneNumber" type="text" id="txtPhoneNumber" size="15" maxlength="12">
                                <span class="textfieldRequiredMsg">
                                <br/>A value is required.</span><span class="textfieldInvalidFormatMsg"><br>
                        Only Number Allowed..</span></span></td>
                    </tr>
                    <tr>
                        <td align="right" valign="top">Date of Birth:</td>
                        <td colspan="2"  align="left" valign="top"><span id="sprytextfield4">
                                <input name="txtDOB" type="text" id="txtDOB" size="33" maxlength="10">
                                <br>
                        <span class="textfieldRequiredMsg">A value is required.</span><span class="textfieldInvalidFormatMsg">Invalid format (dd/mm/yyyy).</span></span></td>
                    </tr>
                    <tr>
                        <td align="right" valign="top">Password:</td>
                        <td colspan="2"  align="left" valign="top"><span id="sprypassword1">
                                <input name="txtPassword" type="password" id="txtPassword" size="33" maxlength="29" placeholder=">= 8 characters">
                                <span class="passwordRequiredMsg"><br/>A value is required.</span><span class="passwordMinCharsMsg"><br>
                        Minimum number of characters not met.</span></span></td>
                    </tr>
                    <tr>
                        <td align="right" valign="top">Confirm Password:</td>
                        <td colspan="2"  align="left" valign="top"><span id="spryconfirm1">
                                <input name="txtCPassword" type="password" id="txtCPassword" size="33" maxlength="29">
                        <span class="confirmRequiredMsg"><br/>A value is required.</span><span class="confirmInvalidMsg"><br/>The values don't match.</span></span></td>
                    </tr>
                    <tr>
                        <td colspan="3"><a href="login.jsp">Already Have an Account?</a>
                        <input name="btnRegister" type="submit" id="btnRegister" accesskey="R" value="Register" /></td>
                    </tr>
                </table>
                <input type="hidden" name="returnType" value="form" />
            </form>

        </div>

        <script type="text/javascript">
            var sprypassword1 = new Spry.Widget.ValidationPassword("sprypassword1", {minChars:8});
            var spryconfirm1 = new Spry.Widget.ValidationConfirm("spryconfirm1", "txtPassword");
            var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1");
            var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2");
            var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3", "integer", {hint:"09123456789"});
            var sprytextfield4 = new Spry.Widget.ValidationTextField("sprytextfield4", "date", {format:"dd/mm/yyyy", hint:"dd/mm/yyyy"});
            var sprytextfield5 = new Spry.Widget.ValidationTextField("sprytextfield5", "email", {hint:"someone@gmail.com"});
        </script>
        <div id="footer"></div>
    </body>
</html>
