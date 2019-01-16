package com.xin.heap;

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
        maxHeap.insert(DefaultObject.builder().score(20).build());
        maxHeap.insert(DefaultObject.builder().score(120).build());
        maxHeap.insert(DefaultObject.builder().score(10).build());
        maxHeap.insert(DefaultObject.builder().score(20).build());
        maxHeap.insert(DefaultObject.builder().score(30).build());

        maxHeap.printArr();
        System.out.println("delete top :");
        maxHeap.deleteHeapTop();
        maxHeap.printArr();

    }


    @Builder
    private static class DefaultObject implements HeapObject {

        private int score;
        @Override
        public int getScore() {
            return score;
        }
    }
}
