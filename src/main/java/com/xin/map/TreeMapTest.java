package com.xin.map;

import com.google.common.collect.Maps;

import java.util.TreeMap;

/**
 * @author three
 * @since 2019/1/16 16:58
 * <p>
 *
 * </p>
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<String, Integer> cityCnt = Maps.newTreeMap();
        cityCnt.put("shanghai", 30);
        cityCnt.put("liaoCheng", 2);
        cityCnt.put("hefei", 13);
        cityCnt.put("chengdu", 5);
        cityCnt.put("beijing", 20);

        System.out.println(cityCnt.firstEntry());

    }
}
