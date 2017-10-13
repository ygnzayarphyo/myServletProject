<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@page import="mm.com.aidatech.utilities.www.DatabaseTransaction;" %>
<%@page import="java.util.ArrayList;" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>hot Topics</title>
        <script src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.5-dist/js/jquery.redirect.js"></script>
        <script src="SpryAssets/StatusDetail.js"></script>
        <script>
            $(function(){
                $("#header").load("header.jsp");
                $("#footer").load("footer.html");
            });
            function myFunction(statusId){                
                window.location="detail.jsp?StatusId="+statusId;
            }             

        </script>
        <link rel="shortcut icon" type="image/x-icon" href="bootstrap-3.3.5-dist/MyLogo.png" />
        <link href="SpryAssets/GeneralForm.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div id="header"></div>

        <div id="main">

            <form action="composer.jsp" method="post">
                <input type="submit" value="Click to Share your though" name="btnPostStatus" style="width:100%;" />
            </form>
            <p>${information} </p>
            <% ArrayList[][] values = new DatabaseTransaction().getStatus(6, 0);
        for (int row = 0; row < values.length; row++) {
            %>
            <table class="consequenceTable" cellpadding="2" style="cursor:pointer;">
                <tr>
                    <th scope="col">
                        <%= values[row][2]%>
                    </th>
                </tr>
                <tr>
                    <td height="90px" onclick="myFunction(<%=values[row][0]%>);">

                        <%= values[row][3]%>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
            </table>
            <% }%>

        </div>
    </body>
</html>