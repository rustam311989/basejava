package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) {
        //установка юид
        Resume resume = new Resume("test");
        // изменение юид через рефлексию
        try {
            Field field = resume.getClass().getDeclaredField("uuid");
            field.setAccessible(true);
            field.set(resume,"заменено на проверка");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // вызов метода toString через рефлексию
        try {
            Method method = resume.getClass().getDeclaredMethod("toString");
            method.setAccessible(true);
            System.out.println(method.invoke(resume));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
