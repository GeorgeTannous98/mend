package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandExecutor {

    public static CmdResult runCommand(String... command) {
        StringBuilder stdout = new StringBuilder();
        StringBuilder stderr = new StringBuilder();
        int exitCode = -1;

        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        String[] shellPrefix = isWindows
                ? new String[]{"cmd.exe", "/c"}
                : new String[]{"bash", "-c"};

        List<String> fullCommand = new ArrayList<>(Arrays.asList(shellPrefix));
        if (isWindows) {
            fullCommand.addAll(Arrays.asList(command));
        } else {

            String joined = String.join(" ", command);
            fullCommand.add(joined);
        }
        try {
            ProcessBuilder pb = new ProcessBuilder(fullCommand);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (
                    BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))
            ) {
                outReader.lines().forEach(line -> stdout.append(line).append(System.lineSeparator()));
                errReader.lines().forEach(line -> stderr.append(line).append(System.lineSeparator()));
            }

            exitCode = process.waitFor();
        } catch (Exception e) {
            stderr.append("Exception: ").append(e.getMessage());
        }

        return new CmdResult(exitCode,
                stdout.toString().trim(),
                stderr.toString().trim());
    }
}