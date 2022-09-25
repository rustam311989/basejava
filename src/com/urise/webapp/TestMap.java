package com.urise.webapp;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("111", "one");
        map.put("222", "two");
        map.put("333", "three");
        map.put("444", "fore");
        System.out.println(map);
        map.put("444","four");
        System.out.println(map);
    }
}
