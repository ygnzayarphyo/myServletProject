/*
 * LoginServlet.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.www;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mm.com.aidatech.utilities.www.DatabaseTransaction;

/**
 *
 * @author Zayar
 */
public class LoginServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			PrintWriter writer = response.getWriter();
			String email = request.getParameter("txtEmail").toString();
			String password = request.getParameter("txtPassword").toString();
			String returnType = request.getParameter("returnType").toString();
			String output = "";

			DatabaseTransaction db = new DatabaseTransaction();
			int isSuccess = db.loginUser(request.getSession(), email, password);
			switch (isSuccess) {
			case 11:
				output = ("<meta http-equiv=\"refresh\" content=\"2;url=index.jsp\" />");
				output += (email + " login success.");
				output += ("<br/><br/>Redirect within 2 seconds.");
				break;
			case 10:
				output = (email + " is not activated.");
				output += ("<a href=\"profile.jsp\">Go to Activate</a>");
				break;
			case 01:
				output = ("User Name Not found.");
				output += ("<a href=\"login.jsp\">Go back.</a>");
				break;
			default:
				output = ("Error Exist During Login process.");
			}

			if (returnType.equals("form")) { // get http header.
				response.setContentType("text/html");
				writer.println(output);
			} else {
				response.setContentType("text/xml; charset=UTF-8");
				String xmlOutput = "<result>";
				xmlOutput += "<userid>"
						+ request.getSession().getAttribute("UserId")
						+ "</userid>";
				xmlOutput += "<email>" + email + "</email>";
				xmlOutput += "<accountType>"
						+ request.getSession().getAttribute("ActivationStatus")
						+ "</accountType>";
				xmlOutput += "<status>" + isSuccess + "</status>";
				xmlOutput += "</result>";
				writer.println(xmlOutput);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
