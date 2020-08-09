package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{
    @Override
    //Шаблонный метод.Ищет резюме c uuid в хранилище и возвращает его индекс. Если резюме не найдено, возвращает -1
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
    //Шаблонный метод. Сохраняет резюме в массиве.
    protected void doSave(Resume resume) {
        storage[size] = resume;
    }
}
