package cn.luxinhuo.concurrent_coding.stage1.sync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;


public class TurnABC {


    public static void main(String[] args) {
//        Object o = new Java.lang.Object();
    }

    public void turnPrint(Object a,Object b,Object c){

        synchronized (a){
            synchronized (b){

            }
        }
    }


}
