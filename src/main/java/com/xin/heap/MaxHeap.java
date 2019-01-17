package com.xin.heap;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author three
 * @since 2019/1/16 17:23
 * <p>
 * 大顶堆
 * </p>
 */
public class MaxHeap<T extends HeapObject> implements Heap<T> {
    /**
     * 默认长度
     */
    private static int sDefaultLen = 20;

    /**
     * 堆容器
     */
    private T[] heapContainer;

    /**
     * 容器长度
     */
    private int size;


    public MaxHeap() {
        heapContainer = (T[]) Array.newInstance(HeapObject.class, sDefaultLen);
    }


    /**
     * 插入数据
     * 插入数据时需要判断 自身有没有父节点
     * 有父节点需要判断，需要与父节点进行位置交换
     * 如果不需要交换直接跳出，因为这是一个有序的堆
     * 交换后需要判断父节点是否还有父节点，有则进行交换
     */
    @Override
    public void insert(T data) {

        resize();

        // 赋值
        heapContainer[size] = data;

        // 判断是否需要进行位置调换
        // 只判断自己和父节点

        // 定义临时位置为最后一个
        compareWithParentAndSet(size);
        // 容量+1
        size++;
    }

    @Override
    public void update(T data) {
        int index = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (heapContainer[i].getData().equals(data.getData())) {
                index = i;
                heapContainer[i] = data;
                break;
            }
        }
        if (index == Integer.MIN_VALUE) {
            //不存在该元素则插入
            insert(data);
            return;
        }
        //存在则更改
        compareWithParentAndSet(index);
    }

    /**
     * 扩容
     */
    private void resize() {
        // 是否到达扩容点
        if (size >= heapContainer.length) {
            // 进行扩容每次扩容 sDefaultLen 长
            heapContainer = Arrays.copyOf(heapContainer, heapContainer.length + sDefaultLen);
        }
    }

    //与父节点比对并且交换
    private void compareWithParentAndSet(int tempIndex) {
        // 通过index来判断是否需要调换条件
        while (tempIndex != 0) {
            // 计算父节点
            int parentIndex = (tempIndex - 1) >> 1;

            // 如果比父节点大 则需要进行位置调换
            if (heapContainer[tempIndex].getScore() > heapContainer[parentIndex].getScore()) {
                // 位置交换的算法
                swap(tempIndex, parentIndex);
            } else {
                break;
            }
            // 当前位置在父节点位置上  如果父节点位置还有父节点 再重新进行位置调整
            tempIndex = parentIndex;
        }
    }

    /**
     * 删除堆只处理堆顶
     * <p>
     * 堆的结构不能随便改变，所以删除不能删除子节点，只能删除堆顶
     * 删除步骤：
     * 1.移除堆顶元素
     * 2.将最后一个元素放在堆顶
     * 3.调整堆
     * 4.堆大小减一
     */
    @Override
    public void deleteHeapTop() {

        // 空堆不可删
        if (isEmpty()) {
            return;
        }

        // 大于一个元素才可以操作
        if (size > 1) {
            // 将最后一个元素放在堆顶
            heapContainer[0] = heapContainer[size - 1];
            heapContainer[size - 1] = null;
        }

        // 将最后一个元素放在堆顶，最大堆的结构已经破坏
        // 需要重新调整堆
        int tempIndex = 0;

        while (true) {
            // 左节点
            int leftIndex = (tempIndex << 1) + 1;
            // 右节点
            int rightIndex = (tempIndex << 1) + 2;

            // 判断左右子树是否存在
            if (leftIndex > size - 1 && rightIndex > size - 1) {
                // 如果越界直接退出
                break;
            }

            // 判断跟左子树比还是根右子树比
            // 默认跟左子树比
            boolean isLeft = true;

            // 如果没有越界存在右子树
            if (rightIndex < size - 1) {
                // 我们先判断左右子树谁大谁小

                // 如果左子树大于右子树 则左子树与父节点比，否则右子树与父节点比
                isLeft = heapContainer[leftIndex].getScore() > heapContainer[rightIndex].getScore();
            }

            if (isLeft) {
                // 如果与左子树比较
                // 需要交换
                if (heapContainer[leftIndex].getScore() > heapContainer[tempIndex].getScore()) {
                    swap(leftIndex, tempIndex);
                    tempIndex = leftIndex;
                } else {
                    break;
                }
            } else {
                // 如果与右子树比较
                if (heapContainer[rightIndex].getScore() > heapContainer[tempIndex].getScore()) {
                    swap(rightIndex, tempIndex);
                    tempIndex = rightIndex;
                } else {
                    break;
                }
            }
        }
        // 删除后容量-1
        size--;
    }

    /**
     * 是否是空堆
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取堆顶元素
     *
     * @return
     */
    @Override
    public T getHeadTop() {
        if (isEmpty()) {
            throw new IllegalStateException("heap is empty.");
        }
        return heapContainer[0];
    }

    /**
     * 打印数组中的存储结构
     */
    @Override
    public void printArr() {
        for (int i = 0; i < size; i++) {
            System.out.println(("MaxHeap:" + JSON.toJSONString(heapContainer[i])));
        }
    }

    /**
     * 交换位置
     */
    private void swap(int index1, int index2) {
        T temp = heapContainer[index1];
        heapContainer[index1] = heapContainer[index2];
        heapContainer[index2] = temp;
    }
}
