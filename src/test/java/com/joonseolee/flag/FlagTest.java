package com.joonseolee.flag;

import org.joonseolee.flag.FlagAtomicRunnable;
import org.joonseolee.flag.FlagBasicRunnable;
import org.joonseolee.flag.FlagVolatileRunnable;
import org.junit.jupiter.api.Test;

class FlagTest {

    @Test
    void flagBasicTest() throws InterruptedException {
        FlagBasicRunnable runnable = new FlagBasicRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3000);

        runnable.stop(false);
    }

    @Test
    void flagVolatileTest() throws InterruptedException {
        FlagVolatileRunnable runnable = new FlagVolatileRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3000);

        runnable.stop(false);
    }

    @Test
    void flagAtomicTest() throws InterruptedException {
        FlagAtomicRunnable runnable = new FlagAtomicRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3000);

        runnable.stop();
    }
}
