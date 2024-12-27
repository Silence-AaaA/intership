package com.Silence;

import com.Silence.domain.*;

import java.util.concurrent.atomic.AtomicInteger;

public class AsyncTest {

    //常数定义 防止魔数
    private final static int NOT_FOUNT = -1;
    private final static MyString ZERO = new MyString("0");
    static MyArrayListEntity<String> list = new MyArrayListEntity();
    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        while(true){
            System.out.println("👉请输入要处理的模式串👈");
            System.out.println("👉请输入要处理的文本👈");
            MyScanner in = new MyScanner(System.in);
            MyString virusStr = in.next();
            MyString peoStr = in.next();
            //对病毒字符串进行处理
            if (virusStr.equals(ZERO) && peoStr.equals(ZERO)){
                break;
            }
            MyString circleVirusStr = new MyString(virusStr +""+ virusStr);
            boolean OneKMP = false;
            boolean TwoKMP = false;
            boolean BF  =false;
            for (int i = 0; i < virusStr.length() && !OneKMP; i++) {
                BF = peoStr.indexOfByBF(circleVirusStr.substring(i, i + virusStr.length()))!=NOT_FOUNT?true:false;
                TwoKMP = peoStr.indexOfByKMP(circleVirusStr.substring(i, i + virusStr.length()))!=NOT_FOUNT?true:false;
                OneKMP = peoStr.indexOfByKMP(circleVirusStr.substring(i, i + virusStr.length()), peoStr)!=NOT_FOUNT?true:false;
            }
            count.incrementAndGet();
            list.add("------------------第"+count +"次------------------------");
            list.add(BF?count+":BF YES":count+":DP NO");
            list.add(OneKMP?count+":KMP-ONE YES":count+":KMP-ONE NO");
            list.add(TwoKMP?count+":KMP-TWO YES":count+":KMP-TWO NO");
            list.add("---------------------------------------------");
        }
        list.traverseAllElement();
    }
}
