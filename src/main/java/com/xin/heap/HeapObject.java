package com.xin.heap;

/**
 * @author three
 * @since 2019/1/16 17:25
 * <p>
 *
 * </p>
 */
public interface HeapObject<T> {
    /**
     * 获取权重
     *
     * @return
     */
    int getScore();

    /**
     * 获取数据
     *
     * @return
     */
    T getData();
}
