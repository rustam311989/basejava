package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private final Resume resume1 = new Resume("uuid1");
    private final Resume resume2 = new Resume("uuid2");
    private final Resume resume3 = new Resume("uuid3");
    private final Resume resume4 = new Resume("uuid4");
    private final Resume resume5ForUpdate = new Resume("uuid2");

    public AbstractArrayStorageTest(Storage storage) {
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
        Assertions.assertArrayEquals(new Resume[]{resume1, resume5ForUpdate, resume3},storage.getAll());
    }

    @Test
    public void save() throws StorageException {
        storage.save(resume4);
        Assertions.assertArrayEquals(new Resume[]{resume1, resume2, resume3, resume4},storage.getAll());
    }

    @Test
    public void get() throws NotExistStorageException {
        Assertions.assertEquals(resume2,storage.get("uuid2"));
    }

    @Test
    public void delete() throws NotExistStorageException {
        storage.delete("uuid1");
        Resume[] getAll = storage.getAll();
        Assertions.assertTrue(
                resume2 == getAll[0] && resume3 == getAll[1] ||
                        resume2 == getAll[1] && resume3 == getAll[0]
        );
    }

    @Test
    public void getAll() {
        Assertions.assertArrayEquals(new Resume[]{resume1, resume2, resume3},storage.getAll());
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

    @Test(expected = StorageException.class)
    public void memoryFull(){
        try{
            for(int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++){
                storage.save(new Resume());
            }
        }catch (StorageException e){
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist(){
        storage.update(resume4);
    }
}