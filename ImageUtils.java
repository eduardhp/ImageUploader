import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utilities for uploading files
 * 
 * @author eduardhp
 */
public class ImageUtils {

	public static String IMAGE_PATH = "/Library/Tomcat/webapps/fileupload/files/";

	/**
	 * Looks for a file by its name, not taking into account the file exception.
	 * 
	 * @param name
	 * @return
	 */
	public static File findImage(String name) {
		File dir = new File(IMAGE_PATH);
		File[] files = dir.listFiles();
		for (File file : files) {
			String filename = file.getName();
			String filenameNoExt = filename.substring(0, filename.indexOf("."));
			if (name.equals(filenameNoExt)) {
				return file;
			}
		}
		return null;
	}

	/**
	 * Get a file extension for a given Part content
	 * 
	 * @param The
	 *            form part content
	 * @return The string extension.
	 */
	public static String getExtension(String content) {
		String[] items = content.split(";");
		String file = "";
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				file = s.substring(s.indexOf("=") + 2, s.length() - 1);
				return file.substring(file.indexOf("."), file.length());
			}
		}
		return file;
	}

	/**
	 * Displays an image. Name is optionally displayed using showName boolean.
	 * 
	 * @param req
	 *            Request
	 * @param resp
	 *            Response
	 * @param showName
	 *            Boolean to show or not the image name
	 * @throws IOException
	 */
	public static void showImage(HttpServletRequest req,
			HttpServletResponse resp, boolean showName) throws IOException {
		String image = req.getPathInfo().toString();
		image = image.substring(1);
		PrintWriter out = resp.getWriter();
		File file = ImageUtils.findImage(image);
		if (null != file) {
			resp.setContentType("text/html");
			if (showName) {
				out.write(image + " image<br/>");
			}
			out.write("<img src='" + "/fileupload/files/" + file.getName()
					+ "'/>");
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
