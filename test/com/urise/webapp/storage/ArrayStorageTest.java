package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractStorageTest {
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test(expected = StorageException.class)
    public void memoryFull(){
        try{
            for(int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++){
                super.getStorage().save(new Resume());
            }
        }catch (StorageException e){
            Assert.fail();
        }
        super.getStorage().save(new Resume());
    }
}