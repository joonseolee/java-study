package org.joonseolee.concurrency.chapter7;

public class BinarySemaphore implements CommonSemaphore {

    private int signal = 1;

    @Override
    public synchronized void acquired() {
        while (this.signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.signal = 0;
    }

    @Override
    public synchronized void release() {
        this.signal = 1;
        this.notify();
    }
}
