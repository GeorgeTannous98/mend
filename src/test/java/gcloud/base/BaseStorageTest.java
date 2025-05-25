package gcloud.base;

import org.testng.annotations.BeforeSuite;
import utils.gcloud.storage.GcloudAuthenticationCommands;
import utils.gcloud.storage.GcloudStorageCommands;

import java.util.UUID;

public abstract class BaseStorageTest {
    protected String bucketName = "mend-bucket";
    protected GcloudStorageCommands gsc;
    private final GcloudAuthenticationCommands auth;

    public BaseStorageTest() {
        gsc = new GcloudStorageCommands();
        String bucket = System.getenv("GCLOUD_STORAGE_BUCKET");
        if (bucket != null) {
            bucketName = bucket;
        }
        auth = new GcloudAuthenticationCommands();
    }

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() throws Exception {
        // TODO: put into script and run inside docker so it would authenticate for all suites
        String res1 = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        if (res1 == null) {
            throw new Exception("Failed to get env variable");
        }
        auth.activateServiceAccount(res1);
        String res2 = System.getenv("GCLOUD_PROJECT_ID");
        if (res2 == null) {
            throw new Exception("Failed to get env variable");
        }
        auth.setProjectId(res2);
    }

//    @AfterSuite(alwaysRun = true)
//    public void tearDownSuite() {
//
//    }
}
