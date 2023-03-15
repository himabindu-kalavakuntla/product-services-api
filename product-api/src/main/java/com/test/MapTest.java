package com.test;

import java.util.*;
import java.util.stream.Collectors;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap();
        map.put(1, "AAA");
        map.put(2, "BBB");
        map.put(3, "AAA");
        map.put(4, "BBB");
        map.put(5, "CCC");

        sortByValue(map);

    }

    private static void sortByValue(Map<Integer, String> map) {

        List<Map.Entry<Integer, String>> list = new LinkedList<Map.Entry<Integer,String>>(map.entrySet());

        Collections.sort(list, (a,b)-> a.getValue().compareTo(b.getValue()));
        Map<Integer, String> res = new LinkedHashMap<>();
        for(Map.Entry<Integer, String> entry: list) {
            res.put(entry.getKey(), entry.getValue());
        }
        System.out.println(res);
    }


}
