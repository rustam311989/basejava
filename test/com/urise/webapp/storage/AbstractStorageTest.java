package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractStorageTest {
    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    private final Resume resume1 = new Resume("uuid1", "1one");
    private final Resume resume2 = new Resume("uuid2","2two");
    private final Resume resume3 = new Resume("uuid3","3three");
    private final Resume resume4 = new Resume("uuid4","4four");
    private final Resume resume5ForUpdate = new Resume("uuid2","2replacedTwo");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setup(){
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(resume5ForUpdate);
        Assertions.assertEquals(storage.get("uuid2"),resume5ForUpdate);
    }

    @Test
    public void save() throws StorageException {
        storage.save(resume4);
        Assertions.assertArrayEquals(new Resume[]{resume1, resume2, resume3, resume4},storage.getAllSorted().toArray());
    }

    @Test
    public void get() throws NotExistStorageException {
        Assertions.assertEquals(resume2,storage.get("uuid2"));
    }

    @Test
    public void delete() throws NotExistStorageException {
        storage.delete("uuid1");
        Assertions.assertArrayEquals(new Resume[]{resume2, resume3},storage.getAllSorted().toArray());
    }

    @Test
    public void getAllSorted() {
        Assertions.assertArrayEquals(new Resume[]{resume1, resume2, resume3},storage.getAllSorted().toArray());
    }

    @Test
    public void size() {
        Assertions.assertEquals(3,storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist(){
        storage.save(resume2);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist(){
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist(){
        storage.update(resume4);
    }
}