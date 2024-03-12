package com.joonseolee.interrupt;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class InterruptTest {

    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("작업중이다용");
            }
            System.out.println("작업 중단!!");
            System.out.println("인터럽트 상태 1 " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("인터럽트 상태 2 " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            worker.interrupt();
            System.out.println("중단 스레드가 작업 스레드를 중단했네..?");
        });

        worker.start();
        stopper.start();
    }
}
