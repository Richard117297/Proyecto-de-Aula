package co.edu.upb.trenes.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class JsonStorageConfig {
    private static final Path BASE_PATH = Paths.get("data", "json");

    private JsonStorageConfig() {
    }

    public static Path basePath() {
        return BASE_PATH;
    }

    public static Path resolve(String fileName) {
        return BASE_PATH.resolve(fileName);
    }
}
