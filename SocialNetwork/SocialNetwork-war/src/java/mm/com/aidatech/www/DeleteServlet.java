/*
 * DeleteServlet.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.www;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mm.com.aidatech.utilities.www.CustomDialog;
import mm.com.aidatech.utilities.www.DatabaseTransaction;
import mm.com.aidatech.utilities.www.PhotoProcessing;

/**
 *
 * @author Zayar
 */
public class DeleteServlet extends HttpServlet {

    private int frmStatus = 0;
    private int frmComment = 0;
    PrintWriter writer = null;

    /**
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String frmStatus = request.getParameter("status");
            String frmComment = request.getParameter("comment");
            String returnFormValue = request.getParameter("Action"); 
            // this is YES or NO result from Confirm Dialog.
            String message = "Are you sure you want to delete?";
            String output="";
            int returnFlag = 00; 
            // to catch database transaction message.

            if (frmStatus != null) {
                setFrmStatus(frmStatus); // temp store status flag value.
                new CustomDialog().makeConfirmForm(response, message, "delete");
            } else {
                if (frmComment != null) {
                    setFrmComment(frmComment);
                    new CustomDialog().makeConfirmForm(response, message, "delete");
                } else {
                    if (returnFormValue.toString().equalsIgnoreCase("YES")) {
                        if (this.frmStatus != 0) {
                            // Do Delete process for status section.
                            returnFlag = new DatabaseTransaction().deleteProcess("tbl_status", "StatusId", this.frmStatus);
                            String photoPath = getServletContext().getRealPath("") + File.separator + "status" +
                                    File.separator + this.frmStatus + ".jpg"; // to clean both photo and status.
                            new PhotoProcessing().deletePhoto(photoPath);
                        } else {
                            // Do Delete process for comment section.
                            returnFlag = new DatabaseTransaction().deleteProcess("tbl_comment", "CommentId", this.frmComment);
                        }
                        output=(returnFlag == 11) ? "Delete Request Success." : "Error Exist.";
                        cleanData();

                    } else {
                        output="No Big Changes make.";
                    }
                    response.setContentType("text/html");
                    writer = response.getWriter();
                    writer.println("<meta http-equiv=\"refresh\" content=\"2;url=status.jsp\" />");
                    writer.println("<link href=\"SpryAssets/GeneralForm.css\" rel=\"stylesheet\" type=\"text/css\">");
                    writer.println("<h3>"+output+"</h3>");
                    writer.println("<br/><br/>Redirect within 2 seconds.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cleanData() {
        this.frmStatus = 0;
        this.frmComment = 0;
    }

    /**
     *
     * @return
     */
    public int getFrmComment() {
        return frmComment;
    }

    /**
     * 
     * @param frmComment
     */
    public void setFrmComment(String frmComment) {
        this.frmComment = Integer.valueOf(frmComment);
    }

    /**
     *
     * @return
     */
    public int getFrmStatus() {
        return frmStatus;
    }

    /**
     *
     * @param frmStatus
     */
    public void setFrmStatus(String frmStatus) {
        this.frmStatus = Integer.valueOf(frmStatus);
    }
}
