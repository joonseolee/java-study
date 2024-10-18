package org.joonseolee.concurrency.chapter7;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (!lock.compareAndSet(false, true)) {

        }
    }

    public void unlock() {
        lock.set(false);
    }
}
