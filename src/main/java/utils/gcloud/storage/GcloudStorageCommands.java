package utils.gcloud.storage;

import utils.CmdResult;
import utils.CommandExecutor;

public class GcloudStorageCommands {
    public void createStorageBucket(String bucketName) {
        CommandExecutor.runCommand("gcloud", "storage", "buckets", "create", "gs://" + bucketName);
    }

    public CmdResult removeStorageBucket(String bucketName) {
        return CommandExecutor.runCommand("gcloud", "storage", "buckets", "delete", "gs://" + bucketName);
    }

    public void uploadFile(String bucketName, String fileName) {
        CommandExecutor.runCommand("gcloud", "storage", "cp", fileName, "gs://" + bucketName + "/");
    }

    public void removeFile(String bucketName, String fileName) {
        CommandExecutor.runCommand("gcloud", "storage", "rm", "gs://" + bucketName + "/" + fileName);
    }

    public CmdResult listBucket(String bucketName) {
        return CommandExecutor.runCommand("gcloud", "storage", "ls", "gs://" + bucketName);
    }

    public CmdResult listAllBuckets() {
        return CommandExecutor.runCommand("gcloud", "storage", "ls");
    }

    public CmdResult moveFile(String source, String destination) {
        return CommandExecutor.runCommand("gcloud", "storage", "mv", source, destination);
    }

    public CmdResult signUrl(String bucketName, String fileName, String impersonatedAcc) {
        return CommandExecutor.runCommand("gcloud", "storage", "sign-url", "gs://" + bucketName + "/" + fileName, "--duration=40s", "--impersonate-service-account=" +
                impersonatedAcc, "--region=us-central1");
    }

    public CmdResult accessRole(String bucketName, String serviceAccount) {
        return CommandExecutor.runCommand("gcloud", "storage", "buckets", "add-iam-policy-binding", bucketName,
                "--member=serviceAccount:" + serviceAccount,
                "--role=roles/storage.objectViewer");
    }

    public CmdResult hashFile(String bucketName, String fileName) {
        return CommandExecutor.runCommand("gcloud", "storage", "hash", "gs://" + bucketName + "/" + fileName);
    }
}
