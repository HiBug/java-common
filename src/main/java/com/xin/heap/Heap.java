package com.xin.heap;

/**
 * @author three
 * @since 2019/1/16 17:22
 * <p>
 *
 * </p>
 */
public interface Heap<T extends HeapObject> {
    /**
     * 插入数据
     */
    void insert(T data);

    /**
     * 更新数据score,
     * 如果不存在则插入
     *
     * @param data
     */
    void update(T data);


    /**
     * 删除堆只处理堆顶
     */
    void deleteHeapTop();

    /**
     * 是否是空堆
     */
    boolean isEmpty();

    /**
     * 获取堆顶元素
     *
     * @return
     */
    T getHeadTop();

    /**
     * 打印数组中的存储结构
     */
    void printArr();
}
