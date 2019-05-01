package cn.luxinhuo.concurrent_coding.stage1.AQS;

import ch.qos.logback.core.net.QueueFactory;

import java.util.PriorityQueue;
import java.util.concurrent.*;

public class QueueLearn {

    private static final ArrayBlockingQueue<String> arrayBlockingQueue= new ArrayBlockingQueue<>(10,true);
    private static final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
    private static final PriorityQueue priorityQueue = new PriorityQueue();// 默认使用自然排序
    private static final SynchronousQueue synchronousQueue = new SynchronousQueue();
    private static final DelayQueue<Delayed> delayQueue = new DelayQueue<>();
    private static final LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();
    private static final LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();

    private static final QueueFactory fa = new QueueFactory();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 20; i++) {
            System.out.println("正在put第"+i+"个元素");
            arrayBlockingQueue.offer("aa"+i);
            arrayBlockingQueue.put("aaa"+i);
            arrayBlockingQueue.add("aaaa"+i);
            linkedBlockingQueue.put("adfd");

            System.out.println(arrayBlockingQueue.poll());
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.remove());
        }
        for (String s :
                arrayBlockingQueue) {
            System.out.println(s);
        }

    }
}
