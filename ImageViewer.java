import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to show images under /image/*
 * Image name is shown
 * 
 * @author eduardhp
 */
@WebServlet("/image/*")
public class ImageViewer extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ImageUtils.showImage(req,resp,true);
	}
}