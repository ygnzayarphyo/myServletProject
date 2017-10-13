/*
 * RegisterServlet.java
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
public class RegisterServlet extends HttpServlet {

    /**
     * RegisterServlet
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String output = "";

			String userName = request.getParameter("txtUserName");
			String email = request.getParameter("txtEmail");
			String phone = request.getParameter("select")
					+ request.getParameter("txtPhoneNumber");
			String dob = request.getParameter("txtDOB");
			String password = request.getParameter("txtPassword");
			String returnType = request.getParameter("returnType");
			// String password = new
			// AES().encrypt(request.getParameter("txtPassword")); // can be
			// used in JDK 1.8

			DatabaseTransaction db = new DatabaseTransaction();
			int isSuccess = db
					.insertUser(userName, email, phone, dob, password);

			switch (isSuccess) {

			case 11:
				output = ("<meta http-equiv=\"refresh\" content=\"2;url=index.jsp\" />");
				output += (userName + " registered: " + isSuccess);
				output += ("<br/><br/>Redirect within 2 seconds.");
				break;
			case 10:
				output = ("User Name: " + userName + " is already exist.<br/>");
				output += ("<a href=\"register.jsp\" align=\"right\">Please Register Again.</a>");
				break;
			default:
				output = ("Something Went Wrong<br/>");
				output += ("<a href=\"register.jsp\" align=\"right\">Go Back!!!</a>");
				// response.sendRedirect("RegisterServlet");
			}
			PrintWriter writer = response.getWriter();
			if (returnType.equals("form")) { // get http header.
				response.setContentType("text/html");
				writer.println(output);
			} else {
				response.setContentType("text/xml; charset=UTF-8");
				String xmlOutput = "<result><username>" + userName
						+ "</username><status>" + isSuccess
						+ "</status></result>";
				writer.println(xmlOutput);
			}

		} catch (Exception e) {
			System.out.println("Something wrong: " + e.getMessage());
		}
	}
}
