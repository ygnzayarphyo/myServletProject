/*
 * CustomDialog.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.utilities.www;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * This class is about implementation of Custom Confirm Dialog.
 */
public class CustomDialog {

	PrintWriter writer = null;

    /**
     *
     * @param response
     * @param message
     * @param returnAddress
     */
    public void makeConfirmForm(HttpServletResponse response, String message,
			String returnAddress) {
		try {
			response.setContentType("text/html");
			writer = response.getWriter();
			writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			writer.println("<head>");
			writer.println("<link href=\"SpryAssets/GeneralForm.css\" rel=\"stylesheet\" type=\"text/css\">");
			writer.println("</head>");

			writer.println("<body>");
			writer.println("<form action='' method='get'>");
			writer.println(message + "<br/><br/>");
			writer.println("<input src=\"" + returnAddress
					+ "\" type=\"submit\" name=\"Action\" value=\"Yes\"/>");
			writer.println("<input src=\"" + returnAddress
					+ "\" type=\"submit\" name=\"Action\" value=\"No\"/>");
			writer.println("</from>");
			writer.println("</body>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
