/*
 * CommitComment.java
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
 * This is servlet about implementation of Comment Submiting.
 */
public class CommitComment extends HttpServlet {

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
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            String statusId = request.getParameter("txtStatusId");
            String userId = request.getParameter("txtUserId");
            String comment = request.getParameter("txtComment");

            DatabaseTransaction db = new DatabaseTransaction();
            int isSuccess = db.insertComment(request.getSession(), statusId,
                    userId, comment);

            switch (isSuccess) {
                case 11:
                    response.sendRedirect("detail.jsp?StatusId=" + statusId);
                    break;
                default:
                    writer.println("Error Exist. Possible problem - No User Login Found. <a href='status.jsp'>Go Back</a>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
