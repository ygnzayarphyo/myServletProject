/*
 * Profile.java
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
import javax.servlet.http.HttpSession;
import mm.com.aidatech.utilities.www.CustomDialog;
import mm.com.aidatech.utilities.www.DatabaseTransaction;

/**
 * 
 * @author Zayar
 */
public class Profile extends HttpServlet {

	private int flag = 0; // determine to change active account to de-active
							// account or vice visa.
	PrintWriter writer = null; // buffer the ActivationStatus Variable.
	private String message = ""; // to prompt confirm message.
	private String userName = "";

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
		writer = response.getWriter();

		String returnType = request.getParameter("returnType").toString();
		String activationStatus = "";
		String userId = "";
		int isSuccess = 00;
		if (returnType.equals("form")) {
			userName = request.getSession().getAttribute("UserName").toString();
			ccString(request.getSession().getAttribute("ActivationStatus")
					.toString());
			new CustomDialog().makeConfirmForm(response, message, "profile");
		} else {
			userId = request.getParameter("userid").toString();
			activationStatus = request.getParameter("accountType").toString(); /*
																				 * Current
																				 * Account
																				 * condition
																				 * .
																				 * if
																				 * accountCondition
																				 * =
																				 * 1
																				 * then
																				 * Change
																				 * to
																				 * deactive
																				 * account
																				 * .
																				 */
			request.getSession().setAttribute("UserId", userId); // if request
																	// from api,
																	// set user
																	// id in
																	// session.
			ccString(activationStatus);
			isSuccess = new DatabaseTransaction().updateActivationStatus(
					request.getSession(), flag);
			response.setContentType("text/xml; charset=UTF-8");
			String xmlOutput = "<result>";
			xmlOutput += "<userid>" + userId + "</userid>";
			xmlOutput += "<accountType>"
					+ request.getSession().getAttribute("ActivationStatus")
							.toString() + "</accountType>";
			xmlOutput += "<status>" + isSuccess + "</status>";
			xmlOutput += "</result>";
			writer.println(xmlOutput);
			request.getSession().setAttribute("UserId", "");
		}
	}

    /**
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		writer = response.getWriter();
		String output = ""; // to print value.

		if (request.getParameter("Action").equalsIgnoreCase("YES")) {
			new DatabaseTransaction().updateActivationStatus(session, flag);
			output += ("<h2>change Success.<h2><br/><br/>");
		} else {
			flag = (flag == 1) ? 0 : 1;
			/*
			 * if user hit cancel, reset flag to its orignal value e.g. if flag
			 * = 1 then change flag to 0; otherwise change flag to 1;
			 */
			output += ("<h2>No Big changes Make.<h2><br/><br/>");
		}
		output += ("<h4>Redirecting within 3 seconds.</h4>");

		response.setContentType("text/html");
		writer.println("<meta http-equiv=\"refresh\" content=\"3;url=profile.jsp\" />");
		writer.println(output);

	}

	private void ccString(String flag) {
		if (Integer.valueOf(flag) == 1) {
			this.flag = 0; // pass value 0(zero) to change de-active account.
			this.message = "Are you sure you want to change <b>active account to de-active account</b> with Name <b>"
					+ userName + "</b>?";

		} else {
			this.flag = 1; // pass value 1 to change active account.
			this.message = "Are you sure you want to change <b>de-active account to active account</b> with Name <b>"
					+ userName + "</b>?";
		}
	}
}
