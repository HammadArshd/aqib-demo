package com.example.aqibdemo.ImplementProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UsingBlockingQueue {
    public static void main(String[] args) {
//        BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(2);
        BlockingQueueUsingWaitNotify<Integer> bq = new BlockingQueueUsingWaitNotify<>(4);
        final int[] count = {0};

        //  Producer
        final Runnable producer = () -> {
            while (true) {
                try {
                    bq.put(count[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(count[0] + ": Added to Queue");
                count[0]++;
            }
        };

        new Thread(producer).start();
        new Thread(producer).start();

        //  Consumer
        final Runnable consumer = () -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Integer i = bq.take();
                    System.out.println(i + ": Removed from queue for processing.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
