package cn.luxinhuo.concurrent_coding.stage1.thread_security;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ThreadSecurity {

    public static void main1(String[] args) {
        StringBuffer sb1 =new StringBuffer();
        StringBuilder sb2 = new StringBuilder();

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            sb1.append("哈哈哈").append(i*i);
        }
        long end1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            sb2.append("嘿嘿嘿").append(i*i);
        }
        long end2 = System.currentTimeMillis();

        log.info("StringBuffer消耗时间：{},长度{}",end1-start1,sb1.length());
        log.info("StringBuilder消耗时间：{},长度{}",end2-end1,sb2.length());
    }

    // 使用ThreadLocal的安全用法
    private static ThreadLocal<DateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    public static void main(String[] args) throws ParseException {
        Date parse = sdf.get().parse("2019-3-9");
        System.out.println(parse.toInstant());


    }

}
