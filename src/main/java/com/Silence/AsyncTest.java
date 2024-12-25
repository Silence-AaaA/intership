package com.Silence;

import com.Silence.pojo.BFEntity;
import com.Silence.pojo.KMPEntity;
import com.Silence.pojo.MyArrayListEntity;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncTest {
    public static MyArrayListEntity<String> list = new MyArrayListEntity();
    public static MyArrayListEntity<Double> Time_V_Map = new MyArrayListEntity();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("👉请输入要处理的模式串👈");
        System.out.println("👉请输入要处理的文本👈");
        String virusStr = in.next();
        String peoStr = in.next();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while(!virusStr.equals("0") && !peoStr.equals("0")){
            atomicInteger.incrementAndGet();
            /**
             *
             * 输入字符串前置处理
             */
            int virLength = virusStr.length();
            //保存当前输入的数据，方便之后统计使用
            String nowVirusStr = virusStr;
            String nowPeoStr = peoStr;
            //将病毒环状DNA设置为环
            virusStr+=virusStr;
            KMPEntity kmpEntity = null;
            BFEntity bfEntity = null;
            for (int i = 0; i < virLength; i++) {
                String subVir = virusStr.substring(i, i+virLength);
                /**
                 * 启动KMP BF算法
                 */
                System.out.println(System.currentTimeMillis());
                if (kmpEntity == null || !kmpEntity.getResult())  {
                    kmpEntity = new KMPEntity(subVir, peoStr);
                    kmpEntity.run();
                }

                System.out.println(System.currentTimeMillis());
                if (bfEntity == null || !bfEntity.getResult()) {
                    bfEntity = new BFEntity(subVir,peoStr);
                    bfEntity.run();
                }
            }
            list.add(atomicInteger + ":针对模式串:"+nowVirusStr+" 以及文本:"+nowPeoStr+" 此次使用KMP运行的的结果为:" + (kmpEntity.getResult()?"YES":"NO") + "，花费了" + kmpEntity.getRunTime() + "纳秒, 调用了" + kmpEntity.getCallNumber()+"次");
            list.add(atomicInteger + ":针对模式串:"+nowVirusStr+" 以及文本:"+nowPeoStr+" 此次使用BF运行的的结果为:"+(bfEntity.getResult()?"YES":"NO") + "，花费了" + bfEntity.getRunTime() + "纳秒, 调用了" + bfEntity.getCallNumber()+"次");
            double rate = (double) kmpEntity.getRunTime() / (double) bfEntity.getRunTime();
            //保留两位小数
            Time_V_Map.add(Double.valueOf(String.format("%.2f",rate)));
            System.out.println("👉请输入要处理的模式串👈");
            System.out.println("👉请输入要处理的文本👈");
            virusStr = in.next();
            peoStr = in.next();
        }
        list.traverse();
        Time_V_Map.createTimeRateMap();
    }
}
