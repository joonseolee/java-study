package org.joonseolee.concurrency.chapter7;

public class SharedResource {
    private int sharedValue = 0;
    private final CommonSemaphore semaphore;

    public SharedResource(CommonSemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void sum() {
        try {
            semaphore.acquired();
            for (int i = 0; i < 10000000; i++) {
                sharedValue++;
            }
        } finally {
            semaphore.release();
        }
    }

    public int getSum() {
        return sharedValue;
    }
}