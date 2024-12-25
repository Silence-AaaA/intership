package com.Silence.pojo;


public class BFEntity extends BaseEntity implements Runnable{
    /**
     * 模式串
     */
    private String pat;
    /**
     * 文本
     */
    private String text;

    public BFEntity(String pat, String text) {
        this.pat = pat;
        this.text = text;
    }

    /**
     * 初始化模式串
     * @param pat
     */
    public BFEntity(String pat) {
        this.pat = pat;
    }

    public BFEntity(){
        pat = null;
        text = null;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * BF算法
     * @param text
     * @return
     */
    public  int bruteForceSearch(String text) {
        if (!getResult()) {
            Long begin = System.nanoTime();
            int n = text.length();
            int m = pat.length();
            // 遍历文本
            for (int i = 0; i <= n - m; i++) {
                int j;
                // 检查当前子串是否与模式匹配
                for (j = 0; j < m; j++) {
                    incrementCallNumber();
                    if (text.charAt(i + j) != pat.charAt(j)) {
                        break;
                    }
                }
                // 如果j等于m，说明匹配成功
                if (j == m) {
                    setResult(true);;
                    Long end = System.nanoTime();
                    setRunTime(end - begin);
                    return i; // 返回匹配的起始索引
                }
            }
            Long end = System.nanoTime();
            setRunTime(end - begin);
            return -1; // 未找到匹配
        }
        return -1;
    }

    /**
     * 开启线程
     */
    @Override
    public void run() {
        if (pat == null || text == null) {
            System.out.println("模式串以及文本不能为空！");
        }else {
            bruteForceSearch(this.text);
        }
    }
}
