/*
 * PictureUploadServlet.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.www;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
public class PictureUploadServlet extends HttpServlet {

	private static String DATA_DIRECTORY = "profile";
	private static final long serialVersionUID = 1L;
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    /**
     * 
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String resultString = "";

		if (!isMultipart) {
			return;
		}

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Sets the size threshold beyond which files are written directly to
		// disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		String uploadFolder = getServletContext().getRealPath("")
				+ File.separator + DATA_DIRECTORY;

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);

		try {
			// Parse the request
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					String extension = getFileExtension(new File(item.getName())
							.getAbsoluteFile());
					if (extension.equalsIgnoreCase("jpg")
							|| extension.equalsIgnoreCase("jpeg")
							|| extension.equalsIgnoreCase("png")) {
						String filePath = uploadFolder + File.separator
								+ request.getSession().getAttribute("UserId")
								+ ".jpg";
						File uploadedFile = new File(filePath);

						item.write(uploadedFile);
						resultString = "Upload success to server.";
					} else {
						resultString = "Only jpg, jpeg and png allowed. Files will not be uploaded.";
					}

				}
			}

		} catch (FileUploadException ex) {
			resultString = "Check your file with the following.<br/>";
			resultString += "File must not bigger than 1MB.";
			System.out
					.println("Uploading Profile Image failed: Check Server Storage.");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
        response.setContentType("text/html");
		writer.println("<meta http-equiv=\"refresh\" content=\"4;url="
				+ DATA_DIRECTORY + ".jsp\" />");
		writer.println(resultString);
		writer.println("<br/><br/>Redirect to " + DATA_DIRECTORY
				+ " within 4 seconds.");

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
