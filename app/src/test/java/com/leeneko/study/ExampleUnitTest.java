package com.leeneko.study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test1() {
        Set<String> list = new HashSet<>();
        list.add("현석샘");
        list.add("은비샘");
        list.add("박현기");
        list.add("침팬치");
        list.add("침팬치");
        assertEquals(4, list.size());
    }

    @Test
    public void addition_isCorrect() {
        List<String> list = new ArrayList<String>(); // ArrayList(검색 속도 빠름) <> LinkedList(삽입, 삭제 속도 빠름)
        assertEquals(0, list.size());

        Map<String, String> arr = new HashMap<>();
        arr.put("week", "1주");
        assertEquals(1, arr.size());
        System.out.println(arr.get("week"));
    }

    int add(int a, int b) {
        return a + b + 1;
    }
}