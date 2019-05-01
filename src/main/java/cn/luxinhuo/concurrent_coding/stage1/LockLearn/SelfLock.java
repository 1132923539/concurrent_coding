package cn.luxinhuo.concurrent_coding.stage1.LockLearn;

import cn.luxinhuo.concurrent_coding.stage1.atomic.Atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

// 自己实现可重入锁与不可重入锁
public class SelfLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    //不可重入
    public void lock(){
        Thread currThread = Thread.currentThread();
        while (true){
            if(owner.compareAndSet(null,currThread)){
                break;
            }
        }
    }

    //可重入
    public void reentrantlock(){
        Thread currThread = Thread.currentThread();
        while (true){
            if(owner.compareAndSet(null,Thread.currentThread()) || owner.compareAndSet(currThread,currThread)){
                break;
            }
        }
    }

    public void unLock(){
        owner.compareAndSet(Thread.currentThread(),null);
    }
}
