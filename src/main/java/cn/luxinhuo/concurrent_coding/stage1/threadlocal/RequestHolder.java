package cn.luxinhuo.concurrent_coding.stage1.threadlocal;

// 对ThreadLocal变量进行操作
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static Long getThreadId() {
        return requestHolder.get();
    }

    public static void add(Long threadId){
        requestHolder.set(threadId);
    }

    public static void remove(){
        requestHolder.remove();
    }

    public static ThreadLocal<Long> getRequestHolder() {
        return requestHolder;
    }
}
