package gcloud.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.gcloud.storage.GcloudStorageCommands;

import java.util.UUID;

public abstract class BaseStorageTest {
    protected String bucketName = "mend-bucket";
    protected String impersonatedAcc = "mend-406@avid-garage-460619-q3.iam.gserviceaccount.com";
    protected GcloudStorageCommands gsc;

    public BaseStorageTest() {
        gsc = new GcloudStorageCommands();
    }

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() throws Exception {
//        gsc.createStorageBucket(bucketName);

    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() throws Exception {
//        gsc.removeStorageBucket(bucketName);
    }
}
