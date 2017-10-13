/*
 * PhotoProcessing.java
 * Version I
 * @author Zayar
 */
package mm.com.aidatech.utilities.www;

import java.io.File;
import javax.servlet.ServletContext;

/**
 *
 * About finding and restting photo in web directory. To clean all photo in both
 * profile and status directories call like 'new
 * PhotoProcessing().cleanAllPhoto(getServletContext())'
 */
public class PhotoProcessing {

    /**
     *
     * @param path
     */
    public void deletePhoto(String path) {
		new File(path).delete();
		System.out.println("Clean File under: " + path);
	}

    /**
     *
     * @param ctx
     */
    public void cleanAllPhoto(ServletContext ctx) {
		String[] folders = { "profile", "status" };
		for (int folderCount = 0; folderCount < folders.length; folderCount++) {
			String path = ctx.getRealPath("") + File.separator
					+ folders[folderCount] + File.separator;
			File directory = new File(path);
			File[] fList = directory.listFiles();
			for (int count = ((folders[folderCount].equalsIgnoreCase("profile")) ? 1
					: 0); count < fList.length; count++) {
				// only default profile picture will keep in profile folder.
				deletePhoto(fList[count].toString());
			}
		}
	}

    /**
     *
     * @param ctx
     * @param folderName
     * @param picName
     * @return
     */
    public boolean findPicture(ServletContext ctx, String folderName,
			String picName) {
		String path = ctx.getRealPath("") + File.separator + folderName
				+ File.separator;
		File directory = new File(path);
		File[] fList = directory.listFiles();

		boolean foundImage = false;
		for (int count = 0; count < fList.length; count++) {
			if (picName.equalsIgnoreCase(fList[count].getName())) {
				foundImage = true;
				break;
			}
		}
		return foundImage;
	}

}
