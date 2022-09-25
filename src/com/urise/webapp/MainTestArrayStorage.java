package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.SortedArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final AbstractArrayStorage STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        //r1.setUuid();
        Resume r2 = new Resume("uuid2");
        //r2.setUuid();
        Resume r3 = new Resume("uuid3");
        //r3.setUuid();
        Resume r4 = new Resume("uuid3");
        //r4.setUuid();

        STORAGE.save(r1);
        STORAGE.save(r2);
        STORAGE.save(r3);

        System.out.println("Get r1: " + STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + STORAGE.size());

        System.out.println("Get dummy: " + STORAGE.get("dummy"));

        printAll();
        STORAGE.update(r4);
        printAll();
        STORAGE.delete(r1.getUuid());
        printAll();
        STORAGE.clear();
        printAll();

        System.out.println("Size: " + STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
