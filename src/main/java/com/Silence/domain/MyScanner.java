package com.Silence.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import static com.Silence.utils.constants.SCANNER_CHARSET_LENGTH;

public class MyScanner {
    //字符缓冲输入流
    private BufferedReader reader;
    //保存字符数组
    private char[] buffer;
    //当前数组索引
    private int currentIndex;
    //数组实际内容长度
    private int length;
    //是否到达结尾
    private boolean endOfStream;

    //初始化
    public MyScanner(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.buffer = new char[SCANNER_CHARSET_LENGTH]; // 初始缓冲区大小
        this.currentIndex = 0;
        this.length = 0;
        this.endOfStream = false;
        fillBuffer();
    }

    // 填充缓冲区
    private void fillBuffer() {
        try {
            length = reader.read(buffer);
            if (length == -1) {
                endOfStream = true; // 到达流的末尾
            } else {
                currentIndex = 0; // 重置索引
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 检查是否有下一个 token
    public boolean hasNext() {
        return !endOfStream || currentIndex < length;
    }

    // 读取下一个整数
    public int nextInt() {
        skipWhitespace();
        String numStr = "";

        while (currentIndex < length && Character.isDigit(buffer[currentIndex])) {
            numStr += buffer[currentIndex++];
        }

        if (numStr.isEmpty()) {
            throw new NoSuchElementException("No integer found");
        }

        return Integer.parseInt(numStr);
    }

    // 读取下一个字符串
    public MyString next() {
        skipWhitespace();
        MyString word = new MyString("");

        while (currentIndex < length && !Character.isWhitespace(buffer[currentIndex])) {
            word =word.concat(new MyString(String.valueOf(buffer[currentIndex++])));
        }

        if (word.isEmpty()) {
            throw new NoSuchElementException("输入为空！");
        }

        return word;
    }

    // 跳过空白字符
    private void skipWhitespace() {
        //检查指定的字符串是否是空字符串
        while (currentIndex < length && Character.isWhitespace(buffer[currentIndex])) {
            currentIndex++;
        }
        if (currentIndex >= length && !endOfStream) {
            fillBuffer();
        }
    }

    // 关闭缓冲字符输入流
    public void close() throws IOException {
        reader.close();
    }
}