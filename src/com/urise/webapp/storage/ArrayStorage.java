package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int searchIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected void insert(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void shift(int index) {
        storage[index] = storage[size - 1];
    }
}
