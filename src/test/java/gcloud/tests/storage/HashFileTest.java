package gcloud.tests.storage;

import gcloud.base.BaseStorageTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.CmdResult;
import utils.PathUtils;

public class HashFileTest extends BaseStorageTest {
    @BeforeClass
    public void setup() {
        String filePath = PathUtils.getTestResourcePath("storage/hashedFile.txt");
        gsc.uploadFile(bucketName, filePath);
    }

    @AfterClass
    public void cleanUp() {
        gsc.removeFile(bucketName, "hashedFile.txt");
    }

    @Test
    public void fileHashingTest() {
        String filePath = "hashedFile.txt";
        CmdResult cmd = gsc.hashFile(bucketName, filePath);
        String output = cmd.getStdout();
        String crc = "";
        String md5 = "";
        for (String line : output.split("\\R")) {
            if (line.startsWith("crc32c_hash:")) {
                crc = line.substring("crc32c_hash:".length()).trim();
                continue;
            }
            if (line.startsWith("md5_hash:")) {
                md5 = line.substring("md5_hash:".length()).trim();
            }
        }

        Assert.assertEquals(crc, "N2Z1eg==");
        Assert.assertEquals(md5, "dOmdBWK8GwSUU5+XDjMtTA==");
    }
}
