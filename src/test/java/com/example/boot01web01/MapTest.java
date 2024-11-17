package com.example.boot01web01;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {
    @Test
    void test() {
        // 创建 LinkedHashMap
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        // 添加元素
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("orange", 3);

        // 按插入顺序遍历
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("-----------------------------");
        map.remove("apple");
        map.put("apple", 4);
        System.out.println(map.size());
        // 按插入顺序遍历
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
