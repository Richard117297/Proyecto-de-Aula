package co.edu.upb.trenes.repositories.storage;

import co.edu.upb.trenes.exceptions.RepositoryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonFileManager {
    private final ObjectMapper objectMapper;

    public JsonFileManager() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void ensureArrayFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path) || Files.size(path) == 0) {
                Files.writeString(path, "[]");
            }
        } catch (IOException e) {
            throw new RepositoryException("No se pudo inicializar " + path, e);
        }
    }

    public void ensureObjectFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path) || Files.size(path) == 0) {
                Files.writeString(path, "{}");
            }
        } catch (IOException e) {
            throw new RepositoryException("No se pudo inicializar " + path, e);
        }
    }

    public <T> List<T> readList(Path path, TypeReference<List<T>> typeReference) {
        ensureArrayFile(path);
        try {
            return objectMapper.readValue(path.toFile(), typeReference);
        } catch (IOException e) {
            throw new RepositoryException("No se pudo leer " + path, e);
        }
    }

    public <T> void writeList(Path path, List<T> items) {
        try {
            Files.createDirectories(path.getParent());
            objectMapper.writeValue(path.toFile(), items == null ? new ArrayList<>() : items);
        } catch (IOException e) {
            throw new RepositoryException("No se pudo escribir " + path, e);
        }
    }

    public <T> T readObject(Path path, Class<T> type) {
        ensureObjectFile(path);
        try {
            return objectMapper.readValue(path.toFile(), type);
        } catch (IOException e) {
            throw new RepositoryException("No se pudo leer " + path, e);
        }
    }

    public <T> void writeObject(Path path, T value) {
        try {
            Files.createDirectories(path.getParent());
            objectMapper.writeValue(path.toFile(), value);
        } catch (IOException e) {
            throw new RepositoryException("No se pudo escribir " + path, e);
        }
    }
}
