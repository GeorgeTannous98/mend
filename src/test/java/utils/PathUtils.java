package utils;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class PathUtils {

    public static String getTestResourcePath(String resourceName) {
        try {
            Path path = Paths.get(Objects.requireNonNull(
                    PathUtils.class.getClassLoader().getResource(resourceName)
            ).toURI());

            return path.toAbsolutePath().toString();
        } catch (URISyntaxException | NullPointerException e) {
            throw new RuntimeException("Resource not found: " + resourceName, e);
        }
    }
}