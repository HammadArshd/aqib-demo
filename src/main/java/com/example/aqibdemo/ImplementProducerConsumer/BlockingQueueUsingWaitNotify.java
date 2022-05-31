package com.example.aqibdemo.ImplementProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueUsingWaitNotify<E> {
    private final Queue<E> queue;
    private int max = 16;
//    private ReentrantLock lock = new ReentrantLock(true);
    private final Object notEmpty = new Object();
    private final Object notFull = new Object();

    public BlockingQueueUsingWaitNotify(int size) {
        queue = new LinkedList<>();
        this.max = size;
    }

    public synchronized void put(E e) throws InterruptedException {
        while (queue.size() == max) {
            notFull.wait();
        }
        queue.add(e);
        notEmpty.notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.size() == 0) {
            notEmpty.wait();
        }
        E item = queue.remove();
        notFull.notifyAll();
        return item;
    }
}
