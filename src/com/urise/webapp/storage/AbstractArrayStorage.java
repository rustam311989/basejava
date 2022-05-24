package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    //шаблонный метод
    public void update(Resume resume) throws NotExistStorageException {
        int index = searchIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Resume " + resume + " updated successfully");
        } else {
            System.out.println("Resume " + resume + " not found");
            throw new NotExistStorageException("Resume " + resume + " not found");
        }
    }

    protected abstract int searchIndex(String uuid);

    @Override
    //шаблонный метод
    public void save(Resume resume) throws StorageException {
        int index = searchIndex(resume.getUuid());
        if (index < 0) {
            if (size < STORAGE_LIMIT) {
                insert(index, resume);
                size++;
                //System.out.println("Resume " + resume + " saved successfully");
            } else {
                System.out.println("It is not possible to keep the memory full");
                throw new StorageException("memory full");
            }
        } else {
            System.out.println("Resume " + resume + " already exists");
            throw new ExistStorageException("Resume " + resume + " already exists");
        }
    }

    protected abstract void insert(int index, Resume resume);

    @Override
    //шаблонный метод
    public Resume get(String uuid) throws NotExistStorageException {
        int index = searchIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not found");
        throw new NotExistStorageException("Resume " + uuid + " not found");
    }

    @Override
    //шаблонный метод
    public void delete(String uuid) throws NotExistStorageException {
        int index = searchIndex(uuid);
        if (index >= 0) {
            shift(index);
            size--;
            System.out.println("Resume " + uuid + " deleted successfully");
        } else {
            System.out.println("Resume " + uuid + " not found");
            throw new NotExistStorageException("Resume " + uuid + " not found");
        }
    }

    protected abstract void shift(int index);

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

}
