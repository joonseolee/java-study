package org.joonseolee.concurrency.chapter7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Chapter7Test {

    @Test
    @DisplayName("BinarySemaphore 테스트")
    void givenSharedResource_whenBinarySemaphore_then() throws InterruptedException {
        SharedResource sharedResource = new SharedResource(new BinarySemaphore());

        Thread thread1 = new Thread(sharedResource::sum);
        Thread thread2 = new Thread(sharedResource::sum);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("합계: " + sharedResource.getSum());
    }

    @Test
    @DisplayName("CountingSemaphore 테스트")
    void givenSharedResource_whenCountingSemaphore_then() throws InterruptedException {
        int permits = 3;
        CommonSemaphore semaphore = new CountingSemaphore(permits);
        SharedResource sharedResource = new SharedResource(semaphore);

        int threadCount = 5;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                synchronized (this) {
                    sharedResource.sum();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        System.out.println("합계: " + sharedResource.getSum());
    }
}