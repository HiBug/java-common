package com.xin.heap;

import com.alibaba.fastjson.JSON;
import lombok.Builder;

/**
 * @author three
 * @since 2019/1/16 17:58
 * <p>
 *
 * </p>
 */
public class HeapTest {
    public static void main(String[] args) {

        Heap<DefaultObject> maxHeap = new MaxHeap<DefaultObject>();
        maxHeap.insert(DefaultObject.builder().score(2).data("a").build());
        maxHeap.insert(DefaultObject.builder().score(12).data("b").build());
        maxHeap.insert(DefaultObject.builder().score(1).data("c").build());
        maxHeap.insert(DefaultObject.builder().score(2).data("d").build());
        maxHeap.insert(DefaultObject.builder().score(3).data("e").build());

        System.out.println("heap:");
        maxHeap.printArr();

        System.out.println("top:");
        System.out.println(JSON.toJSONString(maxHeap.getHeadTop()));

        System.out.println("heap:");
        maxHeap.printArr();

        System.out.println("delete top :");
        maxHeap.deleteHeapTop();
        maxHeap.printArr();

        DefaultObject a = DefaultObject.builder().score(5).data("a").build();

        System.out.println("update:");
        System.out.println(JSON.toJSONString(a));

        maxHeap.update(a);

        System.out.println("heap:");
        maxHeap.printArr();

        a = DefaultObject.builder().score(5).data("f").build();

        System.out.println("update:");
        System.out.println(JSON.toJSONString(a));

        maxHeap.update(a);

        System.out.println("heap:");
        maxHeap.printArr();
    }


    @Builder
    private static class DefaultObject implements HeapObject<String> {

        private int score;

        private String data;

        @Override
        public String getData() {
            return data;
        }

        @Override
        public int getScore() {
            return score;
        }
    }
}
