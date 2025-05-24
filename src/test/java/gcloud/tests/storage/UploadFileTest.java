package gcloud.tests.storage;

import gcloud.base.BaseStorageTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import utils.CmdResult;
import utils.PathUtils;


public class UploadFileTest extends BaseStorageTest {
    @AfterClass
    public void cleanUp() throws Exception {
        gsc.removeFile(bucketName, "number.txt");
    }

    @Test
    public void testUploadFile() throws Exception {
        String testFile = PathUtils.getTestResourcePath("storage/number.txt");

        gsc.uploadFile(bucketName, testFile);

        CmdResult cmd = gsc.listBucket(bucketName);
        String stdout = cmd.getStdout();
        String[] path = stdout.split("/");

        Assert.assertEquals(path[path.length - 1], "number.txt");
    }
}
