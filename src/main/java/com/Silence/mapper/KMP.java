package com.Silence.mapper;

import com.Silence.domain.MyString;

public interface KMP {

    //暴力匹配
    int indexOfByBF(MyString pattern);

    //KMP第一种实现方式
    int indexOfByKMP(MyString pattern);

    //KMP第二种实现方式
    int indexOfByKMP(MyString pattern,MyString text);
}
