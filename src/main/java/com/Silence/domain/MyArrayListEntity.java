package com.Silence.domain;


import java.util.Arrays;

import static com.Silence.utils.constants.SCANNER_CHARSET_LENGTH;

public class MyArrayListEntity<T> {
    private Object[] elements;
    private int size;

    // 构造方法，初始化列表
    public MyArrayListEntity() {
        elements = new Object[10]; // 初始容量
        size = 0;
    }

    // 添加元素
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    // 获取元素
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    // 删除元素
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null; // 清空最后一个元素
    }

    // 获取列表大小
    public int size() {
        return size;
    }

    // 确保容量足够
    private void ensureCapacity() {
        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2); // 扩容
        }
    }

    // 检查索引有效性
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * 遍历当前集合当中的所有元素
     */
    public void traverseAllElement(){
        for (int i1 = 0; i1 < size; i1++) {
            System.out.println(elements[i1]);
        }
    }


    // 转换为字符串
    @Override
    public String toString() {
        return new String(Arrays.toString(elements)); // 返回拼接的字符串
    }
}