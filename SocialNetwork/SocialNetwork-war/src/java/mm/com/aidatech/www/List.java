/*
 * List.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mm.com.aidatech.utilities.www.DatabaseTransaction;

/**
 *
 * @author Zayar
 */
public class List extends HttpServlet {

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
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter writer = response.getWriter();

			String section = request.getParameter("section"); // this must
																// include
																// status or
																// comment
																// table.
			String[] dataFields = new String[3];
			String[] columnFields = new String[3];
			String xmlOutput = "<result>"; // this is to display text.
			boolean processed = false;

			if (section.equalsIgnoreCase("status")) {
				columnFields[0] = "Title";
				columnFields[1] = "Status";
				columnFields[2] = "PostDate";
				dataFields[0] = String.valueOf(request.getParameter("title"));
				dataFields[0] = (dataFields[0].toString().equals("null")) ? ""
						: dataFields[0];
				dataFields[1] = String.valueOf(request.getParameter("status"));
				dataFields[1] = (dataFields[1].toString().equals("null")) ? ""
						: dataFields[1];
				dataFields[2] = String.valueOf(request.getParameter("date"));
				dataFields[2] = (dataFields[2].toString().equals("null")) ? ""
						: dataFields[2];
				processed = true;
			} else if (section.equalsIgnoreCase("comment")) {
				columnFields[0] = "Comment";
				columnFields[1] = "CommentedDate";
				columnFields[2] = ""; // no need but to avoid null pointer
										// excception.
				dataFields[0] = String.valueOf(request.getParameter("comment"));
				dataFields[0] = (dataFields[0].toString().equals("null")) ? ""
						: dataFields[0];
				dataFields[1] = String.valueOf(request.getParameter("date"));
				dataFields[1] = (dataFields[1].toString().equals("null")) ? ""
						: dataFields[1];
				processed = true;
			} else {
				processed = false;
				xmlOutput += "<row>your request section doesn't meet.</row>";
			}
			if (processed) {
				ArrayList[][] result = new DatabaseTransaction().getList(
						section, columnFields, dataFields);
				for (int count = 0; count < result.length; count++) {
					xmlOutput += "<row>";
					xmlOutput += "<number>" + (count + 1) + "</number>";
					xmlOutput += (section.equalsIgnoreCase("status")) ? "<title>"
							+ result[count][0] + "</title>"
							: "";
					xmlOutput += "<message>"
							+ ((section.equalsIgnoreCase("status")) ? result[count][1]
									: result[count][0]) + "</message>";
					xmlOutput += "<date>"
							+ ((section.equalsIgnoreCase("status")) ? result[count][2]
									: result[count][1]) + "</date>";
					xmlOutput += "</row>";
				}
			}
			xmlOutput += "</result>";
			writer.println(xmlOutput);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
