package org.joonseolee.flag;

import java.util.logging.Logger;

public class FlagVolatileRunnable implements Runnable {
    private static final Logger log = Logger.getLogger(FlagVolatileRunnable.class.getSimpleName());
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            log.info("Thread is running");
        }
    }

    public void stop(boolean running) {
        log.info("Stop this thread");
        this.running = running;
    }
}

