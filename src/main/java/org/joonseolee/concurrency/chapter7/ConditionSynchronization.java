package org.joonseolee.concurrency.chapter7;

public class ConditionSynchronization {

    private boolean isAvailable;

    public synchronized void produce() {
        while (isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("생산됨");
        isAvailable = true;
        notify();
    }

    public synchronized void consume() {
        while (!isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("소비됨");
        isAvailable = false;
        notify();
    }
}
