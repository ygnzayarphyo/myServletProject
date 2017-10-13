/*
 * Logout.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.www;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mm.com.aidatech.utilities.www.WebSession;

/**
 *
 * This template is about Logout process of the system.
 */
public class Logout extends HttpServlet {

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
		try {
			new WebSession().destroySession(request);
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
