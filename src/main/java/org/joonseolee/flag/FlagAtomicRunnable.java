package org.joonseolee.flag;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class FlagAtomicRunnable implements Runnable {
    private static final Logger log = Logger.getLogger(FlagAtomicRunnable.class.getSimpleName());
    private final AtomicBoolean running = new AtomicBoolean(true);

    @Override
    public void run() {
        while (running.get()) {
            log.info("Thread is running");
        }
    }

    public void stop() {
        log.info("Stop this thread");
        this.running.set(false);
    }
}
