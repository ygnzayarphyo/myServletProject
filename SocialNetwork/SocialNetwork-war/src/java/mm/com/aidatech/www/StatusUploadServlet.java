/*
 * StatusUploadServlet.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.www;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mm.com.aidatech.utilities.www.DatabaseTransaction;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * This Servlet is about Status uploading including text and/or photo.
 */
public class StatusUploadServlet extends HttpServlet {

    private static String DATA_DIRECTORY = "status";
    private static final long serialVersionUID = 1L;
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 20;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;
    private static String title = "";
    private static String message = "";

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

            if (request.getSession().getAttribute("UserId").toString().isEmpty()) {
                writer.println("<meta http-equiv=\"refresh\" content=\"2;url=login.jsp\" />");
                writer.println("you need to login.<br/> Redirecting to login page");
            } else {
                writer.println("<meta http-equiv=\"refresh\" content=\"3;url=status.jsp\" />");

                int count = 0;
                String resultString = "";
                String extension = "";
                int[] isSuccess = null;
                FileItem fileItemForImage = null;

                // Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Set overall request size constraint
                upload.setSizeMax(MAX_REQUEST_SIZE);

                String uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;
                List<FileItem> multiparts = upload.parseRequest(request);

                // Sets the size threshold beyond which files are written
                // directly to
                // disk.
                factory.setSizeThreshold(MAX_MEMORY_SIZE);

                // Sets the directory used to temporarily store files that are
                // larger
                // than the configured size threshold. We use temporary
                // directory for
                // java
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

                for (FileItem item : multiparts) {

                    if (item.isFormField()) {
                        if (count == 1) { // count start from 1 to omit post
                            // value from multipart form data.
                            title = item.getString();
                        } else if (count == 2) {
                            message = item.getString();
                        } else if (count == 3) {
                            /*
                             * only count flag at 3 will do this task once. To
                             * avoid condition like picture is not clipped with
                             * text status
                             */

                            DatabaseTransaction db = new DatabaseTransaction();
                            isSuccess = db.insertStatus(request.getSession(),
                                    title, message);
                            System.out.println("Still ok:" + isSuccess[0] + "," + isSuccess[1]);
                            if (!extension.isEmpty()) {
                                // Start upload Image file to server.
                                if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png")) {
                                    try {
                                        String filePath = uploadFolder + File.separator + String.valueOf(isSuccess[1]) + ".jpg";
                                        File uploadedFile = new File(filePath);

                                        fileItemForImage.write(uploadedFile);
                                        resultString = "Upload success to server.";
                                    } catch (FileUploadException e) {
                                        resultString = "Check your file with the following.<br/>";
                                        resultString += "File must not bigger than 10MB.";
                                        System.out.println("Uploading Profile Image failed: Check Server Storage.");
                                    }
                                } else {
                                    resultString = "Only jpg, jpeg and png allowed. Files will not be uploaded.";
                                }
                            }
                        }
                        count++;
                    } else {
                        fileItemForImage = item;/*
                         * just catch File Extension to
                         * Upload Image. This is like
                         * uploaded image is stored in
                         * fileItemForImage's buffer or
                         * cache memory.
                         */
                        extension = getFileExtension(new File(item.getName()).getAbsoluteFile());
                    }
                }

                switch (isSuccess[0]) {
                    case 11:
                        writer.println("<h1>Upload success.<br/>" + resultString + "<br/></h1>");
                        writer.println("Redirect within 3 seconds...");
                        break;
                    default:
                        writer.println("Something Went Wrong<br/>");
                        writer.println("<a href=\"composer.jsp\" align=\"right\">Go Back!!!</a>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
