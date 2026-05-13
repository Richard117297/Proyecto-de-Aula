package co.edu.upb.trenes.repositories.impl;

import co.edu.upb.trenes.config.JsonStorageConfig;
import co.edu.upb.trenes.repositories.JsonRepository;
import co.edu.upb.trenes.repositories.storage.JsonFileManager;
import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractJsonRepository<T> implements JsonRepository<T, String> {
    private final Path path;
    private final TypeReference<List<T>> typeReference;
    private final Function<T, String> idExtractor;
    private final JsonFileManager fileManager;

    protected AbstractJsonRepository(String fileName, TypeReference<List<T>> typeReference, Function<T, String> idExtractor) {
        this.path = JsonStorageConfig.resolve(fileName);
        this.typeReference = typeReference;
        this.idExtractor = idExtractor;
        this.fileManager = new JsonFileManager();
    }

    @Override
    public List<T> findAll() {
        return fileManager.readList(path, typeReference);
    }

    @Override
    public Optional<T> findById(String id) {
        return findAll().stream().filter(item -> idExtractor.apply(item).equals(id)).findFirst();
    }

    @Override
    public T save(T entity) {
        List<T> items = findAll();
        items.add(entity);
        fileManager.writeList(path, items);
        return entity;
    }

    @Override
    public T update(T entity) {
        List<T> items = findAll();
        String id = idExtractor.apply(entity);
        for (int i = 0; i < items.size(); i++) {
            if (idExtractor.apply(items.get(i)).equals(id)) {
                items.set(i, entity);
                fileManager.writeList(path, items);
                return entity;
            }
        }
        items.add(entity);
        fileManager.writeList(path, items);
        return entity;
    }

    @Override
    public boolean deleteById(String id) {
        List<T> items = findAll();
        boolean removed = items.removeIf(item -> idExtractor.apply(item).equals(id));
        if (removed) {
            fileManager.writeList(path, items);
        }
        return removed;
    }
}
