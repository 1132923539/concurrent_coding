package cn.luxinhuo.concurrent_coding.stage1.sync;

public class Singleton2 {

    //这里必须使用 volatile 修饰，否则会因指令重排有线程安全问题
    private volatile static  Singleton2 singleton2 = null;

    private Singleton2(){}

    public static Singleton2 getInstance(){
        if (singleton2 == null){
            synchronized (Singleton2.class){
                if (singleton2 == null) singleton2 = new Singleton2();
            }
        }
        return singleton2;
    }
}
