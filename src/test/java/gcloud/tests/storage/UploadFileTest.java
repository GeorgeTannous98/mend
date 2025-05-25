package gcloud.tests.storage;

import gcloud.base.BaseStorageTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import utils.CmdResult;
import utils.PathUtils;


public class UploadFileTest extends BaseStorageTest {
    @AfterClass
    public void cleanUp() {
        gsc.removeFile(bucketName, "number.txt");
    }

    @Test
    public void testUploadFile() {
        String testFile = PathUtils.getTestResourcePath("storage/number.txt");

        gsc.uploadFile(bucketName, testFile);

        CmdResult cmd = gsc.listBucket(bucketName);

        Assert.assertTrue(cmd.isSuccess());
        Assert.assertTrue(cmd.getStdout().contains("number.txt"));
    }
}
