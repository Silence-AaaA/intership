package com.Silence.pojo;

public class MyQueueEntity<T> {
    /**
     * 元素数组
     */
    private T[] queue;
    /**
     * 头节点
     */
    private int front;
    /**
     * 尾节点
     */
    private int rear;
    /**
     * 数组长度
     */
    private int capacity;
    /**
     * 当前队列含有的元素个数
     */
    private int size;
    /**
     * 默认数组元素大小
     */
    private final int DEFAULT_CAPACITY_SIZE = 50;

    /**
     * 初始化队列元素个数
     * @param capacity
     */
    public MyQueueEntity(int capacity) {
        this.capacity = capacity;
        queue = (T[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public MyQueueEntity() {
        front = 0;
        rear = -1;
        size = 0;
        capacity = DEFAULT_CAPACITY_SIZE;
        queue = (T[]) new Object[DEFAULT_CAPACITY_SIZE];
    }

    /**
     * 入队
     * @param data
     */
    public void enqueue(T data) {
        if (size == capacity) {
            throw new IllegalStateException("队列已满！");
        }
        rear = (rear + 1) % capacity;
        queue[rear] = data;
        size++;
    }

    /**
     * 出队
     * @return
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("队列为空！");
        }
        T data = queue[front];
        front = (front + 1) % capacity;
        size--;
        return data;
    }

    /**
     * 检查队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取队列大小
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 查看队列头部元素
     * @return
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("当前队列为空！");
        }
        return queue[front];
    }

    /**
     * 查看当前队列的所有元素
     * @return
     */
    public T[] peekAll(){
        if (isEmpty()){
            throw new IllegalStateException("当前队列为空！");
        }
        T[] data = (T[]) new Object[size];
        for (int i = front; i <= rear; i++) {
            data[i] = queue[i];
        }
        return data;
    }
}
