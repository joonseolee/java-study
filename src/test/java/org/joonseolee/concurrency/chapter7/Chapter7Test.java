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

    @Test
    @DisplayName("ConditionSynchronization 테스트")
    void given_whenConditionSynchronization_then() {
        ConditionSynchronization conditionSynchronization = new ConditionSynchronization();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                conditionSynchronization.produce();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                conditionSynchronization.consume();
            }
        }).start();
    }

    @Test
    @DisplayName("SpinLock 테스트")
    void given_when_then() throws InterruptedException {
        SpinLock spinLock = new SpinLock();

        Runnable task = () -> {
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + " 가 락을 획득함");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(Thread.currentThread().getName() + " 가 락을 해제");
                spinLock.unlock();
                System.out.println(Thread.currentThread().getName() + " 가 락을 해제 완료");
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}