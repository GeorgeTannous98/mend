package gcloud.tests.storage;

import gcloud.base.BaseStorageTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.CmdResult;
import org.testng.Assert;
import utils.PathUtils;

public class RenameTest extends BaseStorageTest {

    @BeforeClass
    public void setup() throws Exception {
        String filePath = PathUtils.getTestResourcePath("storage/renameTest.txt");
        gsc.uploadFile(bucketName, filePath);
    }

    @AfterClass
    public void cleanUp() {
        gsc.removeFile(bucketName, "renameTest.txt");
        gsc.removeFile(bucketName, "renamed.txt");
    }

    @Test
    public void moveFileTest() {
        String source = "gs://" + bucketName + "/" + "renameTest.txt";
        String destination = "gs://" + bucketName + "/" + "renamed.txt";

        CmdResult cmdMove = gsc.moveFile(source, destination);

        Assert.assertTrue(cmdMove.isSuccess());

        CmdResult cmdListFiles = gsc.listBucket(bucketName);

        Assert.assertTrue(cmdListFiles.isSuccess());
        Assert.assertTrue(cmdListFiles.getStdout().contains("renamed.txt"));
    }
}
