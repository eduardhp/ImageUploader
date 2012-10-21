import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class TestImageUtils {

	/**
	 * Test for finImageMethod. Strategy: Create a file with a given extension
	 * and find for it using only its name.
	 */
	@Test
	public void testFindImage() {
		File file = new File(ImageUtils.IMAGE_PATH + "blue.jpg");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File findfile = ImageUtils.findImage("blue");
		if (!findfile.exists()) {
			fail("File not found");
		}

		findfile = ImageUtils.findImage("blue2");
		if (null != findfile && findfile.exists()) {
			fail("File found and it should not");
		}

		file.delete();
	}

	@Test
	public void testGetExtension() {
		String content = "form-data; name=\"select\"; filename=\"blue.jpg\"";
		String extension = ImageUtils.getExtension(content);
		if (!extension.equals(".jpg"))
		{
			fail("INcorrect extension");
		}
	}

}
