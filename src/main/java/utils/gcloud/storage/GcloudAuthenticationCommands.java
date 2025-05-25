package utils.gcloud.storage;

import utils.CommandExecutor;

public class GcloudAuthenticationCommands {

    public void activateServiceAccount(String keyFile){
    CommandExecutor.runCommand(
            "gcloud","auth","activate-service-account",
            "--key-file=" + keyFile
        );
        }
    public void setProjectId(String projectId){
        CommandExecutor.runCommand(
                "gcloud", "config", "set", "project",
                projectId
        );
    }

}
