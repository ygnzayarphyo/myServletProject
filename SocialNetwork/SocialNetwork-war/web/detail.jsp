<%--
   - Author: @Zayar
   - Date: Oct 10, 2017 1:20:51 AM
   - Description:   More detail view about individual status with comment section.
   -                If user posted this status or comment, he/she can delete.
   --%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="mm.com.aidatech.utilities.www.DatabaseTransaction;" %>
<%@page import="mm.com.aidatech.utilities.www.PhotoProcessing" %>
<%@page import="javax.servlet.jsp.JspWriter" %>
<%@page import="java.util.ArrayList;" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Detail</title>
        <script src="SpryAssets/SpryAccordion.js" type="text/javascript"></script>
        <link href="SpryAssets/SpryAccordion.css" rel="stylesheet" type="text/css" />
        <link href="SpryAssets/GeneralForm.css" rel="stylesheet" type="text/css">
        <script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
        <script src="SpryAssets/SpryValidationTextarea.js" type="text/javascript"></script>
        <link rel="shortcut icon" type="image/x-icon" href="bootstrap-3.3.5-dist/MyLogo.png" />
        <link href="SpryAssets/GeneralForm.css" rel="stylesheet" type="text/css" />
        <link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
        <link href="SpryAssets/SpryValidationTextarea.css" rel="stylesheet" type="text/css" />

        <style type="text/css">
            #apDiv1 {
                position:absolute;
                width:200px;
                height:115px;
                z-index:1;
                left: 188px;
                top: 277px;
            }
        </style>

        <script type="text/javascript" src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#header").load("header.jsp");
            });
        </script>
    </head>

    <body>

        <div id="header"></div>

        <div id="main">
            <%

        int requestId = Integer.valueOf(request.getParameter("StatusId"));
        ArrayList[][] values = new DatabaseTransaction().getStatus(5, requestId);
        String loginUserId = String.valueOf(request.getSession().getAttribute("UserId"));
        loginUserId = (loginUserId == null) ? "" : loginUserId;

        String statusId = values[0][0].toString().replace("[", "").replace("]", "");
        String userId = values[0][1].toString().toString().replace("[", "").replace("]", "");
        String title = values[0][2].toString();
        String status = values[0][3].toString();
        String postDate = values[0][4].toString();

            %>
            <table width="100%" align="center">
                <tr>
                    <td width="75%">
                        <p align="left"><%=postDate%>&nbsp;&nbsp;
                            <% if (userId.equals(loginUserId)) {%>
                            <span class="glyphicon glyphicon-trash"></span>
                            <a href="delete?status=<%=statusId%>" style="background-color:red;">DELETE</a>
                            <% }%>
                        </p>
                        <div id="Accordion1" class="Accordion" tabindex="0" style="height:400px;">
                            <div class="AccordionPanel">
                                <div class="AccordionPanelTab" align="left"><%=title%></div>
                                <div class="AccordionPanelContent" align="left">
                                    <p><%=status%></p>
                                    <p>&nbsp;</p>
                                </div>
                            </div>
                        </div>
                    </td>
                    <td width="25%">
                        <%
        String picName = statusId + ".jpg";
        boolean foundImage = new PhotoProcessing().findPicture(getServletContext(), "status", picName);
        if (foundImage) {
            out.println("<img src=status/" + picName +
                    " width=\"247\" height=\"198\" alt=\"" + title + "\" /><br/>");
        }
                        %>
                    </td>
                </tr>
            </table>


            <%
        if (loginUserId == null) {
            %>
            <a href="login.jsp" id="loginToComment">Login</a> To Comment This Status.
            <%        } else {%>
            <a href="#comment" data-toggle="collapse">
                Comment Section
            </a>
            <div id="comment" class="collapse">
                <form id="frmComment" name="frmComment" method="post" action="commit">
                    <div id="CollapsiblePanel1" class="CollapsiblePanel">
                        <div class="CollapsiblePanelContent">
                            <br/>
                            <p><span id="sprytextarea1">
                                    <textarea name="txtComment" id="txtComment" cols="45" rows="5"></textarea>
                                    <br/>
                                    <span id="countsprytextarea1">&nbsp;</span><span class="textareaRequiredMsg">A value is required.</span><span class="textareaMaxCharsMsg">
                                        Exceeded maximum number of characters.
                                    </span>
                            </span></p>
                            <p>
                                <input type="submit" name="btnComment" id="btnComment" value="Submit" />
                            </p>
                            <br/>
                        </div>
                    </div>
                    <input type="hidden" name="txtStatusId" value="<%= statusId%>" />
                    <input type="hidden" name="txtUserId" value="${UserId}" />
                </form>

            </div>
            <%        }
            %>

            <br/>
            <br/>

            <div>
                <table>
                    <%
        ArrayList[][] comment = new DatabaseTransaction().getComment(statusId);
        for (int count = 0; count < comment.length; count++) {
            String commentId = comment[count][0].toString().replace("[", "").replace("]", "");
            String commentedUserId = comment[count][1].toString().replace("[", "").replace("]", "");
            String commentedText = comment[count][2].toString().replace("[", "").replace("]", "");
            String commentedDate = comment[count][3].toString();
            String userName = comment[count][4].toString();
                    %>

                    <tr>
                        <td align="left">
                            Posted By: <%=userName%>&nbsp;&nbsp;
                            <% if (commentedUserId.equals(loginUserId)) {%>
                            <span class="glyphicon glyphicon-trash"></span>
                            <a href="delete?comment=<%=commentId%>" style="background-color:red;">DELETE</a>
                            <% }%>
                        </td>
                    </tr>
                    <tr>
                        <td align="left">Posted Date: <%=commentedDate%></td>
                    </tr>
                    <tr>
                        <td align="left"><%=commentedText%></td>
                    </tr>
                    <tr><td>---------!!---------</td></tr>
                    <%   } // End of Comment Section.%>
                </table>
            </div>
        </div>
        <br/>
        <br/>
        <script type="text/javascript">
            var Accordion1 = new Spry.Widget.Accordion("Accordion1");
            var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1");
            var sprytextarea1 = new Spry.Widget.ValidationTextarea("sprytextarea1", {maxChars:499, counterId:"countsprytextarea1", hint:"Leave comment"});
        </script>

    </body>
</html>