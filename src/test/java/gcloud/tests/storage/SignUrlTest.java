package gcloud.tests.storage;

import gcloud.base.BaseStorageTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.microsoft.playwright.*;
import utils.CmdResult;
import utils.PathUtils;

public class SignUrlTest extends BaseStorageTest {

    @BeforeClass
    public void setup() {
        String filePath = PathUtils.getTestResourcePath("storage/login.html");
        gsc.uploadFile(bucketName, filePath);
    }

    @AfterClass
    public void cleanUp() {
        gsc.removeFile(bucketName, "login.html");
    }

    @Test
    public void signUrlTest() throws Exception {

        CmdResult cmd = gsc.signUrl(bucketName, "login.html", impersonatedAcc);

        String stdout = cmd.getStdout();
        String signedUrl = parseSignUrl(stdout);

        if (signedUrl == null) {
            String stderr = cmd.getStderr();
            String msg = stderr + " " + stdout;
            throw new Exception(msg);
        }

        // ensure that signed URLs are actually accessible and do not trigger phishing warnings.
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();
            page.navigate(signedUrl);
            String content = page.content();
            String expectedContent = "<html><head></head><body>\n" +
                    "<form action=\"https://example.com\">\n" +
                    "    <input type=\"text\" name=\"email\">\n" +
                    "    <input type=\"password\" name=\"password\">\n" +
                    "    <button type=\"submit\">Login</button>\n" +
                    "</form>\n" +
                    "\n" +
                    "</body></html>";

            Assert.assertEquals(content, expectedContent);
        }

    }

    private String parseSignUrl(String stdout) {
        for (String line : stdout.split("\\R")) {
            if (line.startsWith("signed_url:")) {
                return line.substring("signed_url:".length()).trim();
            }
        }
        return null;
    }
}
