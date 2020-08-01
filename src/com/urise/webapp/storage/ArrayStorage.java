package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    private int resumeSearch(Resume resume){
        // Ищет ресюме в хранилище и возвращает его индекс. Если резюме не найдено, возвращает -1
        int indexResume = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(resume.uuid)) {
                indexResume = i;
                break;
            }
        }
        return indexResume;
    }
    private int resumeSearch(String uuid){
        // Ищет ресюме c uuid в хранилище и возвращает его индекс. Если резюме не найдено, возвращает -1
        int indexResume = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                indexResume = i;
                break;
            }
        }
        return indexResume;
    }

    public void clear() {
        Arrays.fill(storage,0, size,null);
        size = 0;
    }

    public void update(Resume resume){
        int indexUpdateResume = resumeSearch(resume);
        if (indexUpdateResume != -1) {
            storage[indexUpdateResume] = resume;
            System.out.println("Resume " + resume + " updated successfully");
        }
        else {
            System.out.println("Resume " + resume + " not found");
        }
    }

    public void save(Resume r) {
        int indexGetResume = resumeSearch(r);
        if (indexGetResume == -1) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
                System.out.println("Resume " + r + " saved successfully");
            }
            else {
                System.out.println("It is not possible to keep the memory full");
            }
        }
        else {
            System.out.println("This resume already exists");
        }

    }

    public Resume get(String uuid) {
        int indexGetResume = resumeSearch(uuid);
        if (indexGetResume != -1) {
            return storage[indexGetResume];
        }
        else {
            System.out.println("Resume "+ uuid + " not found");
            return null;
        }
    }

    public void delete(String uuid) {
        int indexDelResume = resumeSearch(uuid);
        if (indexDelResume != -1) {
            for (int i = indexDelResume; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
            System.out.println("Resume " + uuid + " deleted successfully");
        }
        else {
            System.out.println("Resume " + uuid + " not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage,0, size);
    }

    public int size() {
        return size;
    }
}
