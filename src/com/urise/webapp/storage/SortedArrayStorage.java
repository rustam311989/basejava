package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    protected int searchIndex(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        return Arrays.binarySearch(getAll(),r);
    }

    @Override
    protected void doSave(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (resume.compareTo(storage[i]) < 0) {
                System.arraycopy(storage,i,storage,i+1,size-i);
                storage[i] = resume;
                return;
            }
        }
        storage[size] = resume;
    }
}
