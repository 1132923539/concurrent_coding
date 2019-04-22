package cn.luxinhuo.concurrent_coding.stage1.sync;

public class Singleton3 {
    private Singleton3(){}

    public static Singleton3 getInstance(){
        return MySingleton.INSTANCE.getInstance();
    }

    private enum MySingleton{
        INSTANCE;

        private Singleton3 singleton3;

        // jvm保证此方法最多只会被调用一次
        MySingleton(){
            singleton3 = new Singleton3();
        }

        public Singleton3 getInstance(){
            return singleton3;
        }
    }
}
