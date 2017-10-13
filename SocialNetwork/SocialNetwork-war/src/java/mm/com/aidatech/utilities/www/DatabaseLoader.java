/*
 * DatabaseLoader.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.utilities.www;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zayar
 */
public class DatabaseLoader extends HttpServlet {

	private static String dbUrl = "";
	private static String dbUserName = "";
	private static String dbPassword = "";

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
            
			Properties props = new Properties();
			props.load(getServletContext().getResourceAsStream(
					"/WEB-INF/db.conf"));
			setDbUrl(props.getProperty("dbUrl"));
			setDbUserName(props.getProperty("dbUserName"));
			setDbPassword(props.getProperty("dbPassword"));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.jsp");
	}

    /**
     *
     * @return
     */
    public static String getDbPassword() {
		return dbPassword;
	}

    /**
     *
     * @param dbPassword
     */
    public static void setDbPassword(String dbPassword) {
		DatabaseLoader.dbPassword = dbPassword;
	}

    /**
     *
     * @return
     */
    public static String getDbUrl() {
		return dbUrl;
	}

    /**
     *
     * @param dbUrl
     */
    public static void setDbUrl(String dbUrl) {
		DatabaseLoader.dbUrl = dbUrl;
	}

    /**
     *
     * @return
     */
    public static String getDbUserName() {
		return dbUserName;
	}

    /**
     *
     * @param dbUserName
     */
    public static void setDbUserName(String dbUserName) {
		DatabaseLoader.dbUserName = dbUserName;
	}
}
