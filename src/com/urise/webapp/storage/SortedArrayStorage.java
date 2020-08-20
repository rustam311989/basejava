package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int searchIndex(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        return Arrays.binarySearch(getAll(), r);
    }

    @Override
    protected void doSave(int index, Resume resume) {
        int realIndex = -index - 1;
        System.arraycopy(storage, realIndex, storage, realIndex + 1, size - realIndex);
        storage[realIndex] = resume;
    }

    @Override
    protected void shift(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}
