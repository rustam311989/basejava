package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    private static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Resume " + resume + " updated successfully");
        } else {
            System.out.println("Resume " + resume + " not found");
        }
    }

    @Override
    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index < 0) {
            if (size < STORAGE_LIMIT) {
                doSave(resume);
                size++;
                System.out.println("Resume " + resume + " saved successfully");
            } else {
                System.out.println("It is not possible to keep the memory full");
            }
        } else {
            System.out.println("This resume already exists");
        }
    }
    //Шаблонный метод. Сохраняет резюме в массиве.
    protected abstract void doSave(Resume resume);

    @Override
    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not found");
        return null;
    }

    @Override
    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index >= 0) {
            if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
            size--;
            System.out.println("Resume " + uuid + " deleted successfully");
        } else {
            System.out.println("Resume " + uuid + " not found");
        }
    }

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

    // Шаблонный метод. Ищет резюме c uuid в хранилище и возвращает его индекс. Если резюме не найдено, возвращает -1
    protected abstract int searchIndex(String uuid);
}
