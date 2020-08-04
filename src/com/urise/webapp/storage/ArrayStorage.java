package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume " + resume + " updated successfully");
        } else {
            System.out.println("Resume " + resume + " not found");
        }
    }

    public void save(Resume resume) {
        int index = searchIndex(resume.getUuid());
        if (index == -1) {
            if (size < storage.length) {
                storage[size] = resume;
                size++;
                System.out.println("Resume " + resume + " saved successfully");
            } else {
                System.out.println("It is not possible to keep the memory full");
            }
        } else {
            System.out.println("This resume already exists");
        }

    }

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not found");
        return null;
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index != -1) {
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

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int searchIndex(String uuid) {
        // Ищет резюме c uuid в хранилище и возвращает его индекс. Если резюме не найдено, возвращает -1
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
