package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapResumeStorage extends AbstractStorage{
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put(((Resume)searchKey).getUuid(),r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(((Resume)searchKey).getUuid());
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(((Resume)searchKey).getUuid(),r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return map.values()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return map.size();
    }
}
