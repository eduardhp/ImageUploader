import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Upload servlet to upload images. Receives image name and an image.
 * 
 * @author eduardhp
 */
@WebServlet("/upload")
@MultipartConfig(location = "/Library/Tomcat/webapps/fileupload/files/")
public class ImageUploader extends HttpServlet {

	private static final String view = "/WEB-INF/pages/form2.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher(view).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String fullname;
		Boolean imageExists = false;
		String message = "";

		// Process the request
		String name = req.getParameter("name");
		Part part = req.getPart("select");

		// Process errors
		if (null == name || name.length() == 0 || null == part) {
			req.setAttribute("message", "error");
			req.getRequestDispatcher(view).forward(req, resp);
		} else {

			// Find if image name exists(check for different exceptions) and
			// delete it if so.
			File file = ImageUtils.findImage(name);
			if (null != file) {
				imageExists = true;
				file.delete();
			}

			// Process the file and save it to the server
			String content = part.getHeader("content-disposition");
			fullname = name + ImageUtils.getExtension(content);
			part.write(fullname);

			// Show message depending on if it existed before.
			if (imageExists) {
				message = name + " image changed";
			} else {
				message = name + " image updated";
			}

			// Return with message
			req.setAttribute("message", message);
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}
}
