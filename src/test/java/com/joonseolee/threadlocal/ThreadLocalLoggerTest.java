package com.joonseolee.threadlocal;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalLoggerTest {

    private static final ThreadLocal<List<String>> THREAD_LOG = ThreadLocal.withInitial(ArrayList::new);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new LogWorker());
        thread1.start();
        Thread thread2 = new Thread(new LogWorker());
        thread2.start();
        Thread thread3 = new Thread(new LogWorker());
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    private static void addLog(String log) {
        THREAD_LOG.get().add(log);
    }

    private static void printLog() {
        List<String> logs = THREAD_LOG.get();
        System.out.println(String.format("[%s] %s", Thread.currentThread().getName(), String.join("->", logs)));
    }

    private static void clearLog() {
        THREAD_LOG.remove();
    }

    static class ServiceA {
        public void process() {
            addLog("ServiceA 로직 수행");
        }
    }

    static class ServiceB {
        public void process() {
            addLog("ServiceB 로직 수행");
        }
    }

    static class ServiceC {
        public void process() {
            addLog("ServiceC 로직 수행");
        }
    }

    static class LogWorker implements Runnable {

        @Override
        public void run() {
            ServiceA serviceA = new ServiceA();
            ServiceB serviceB = new ServiceB();
            ServiceC serviceC = new ServiceC();

            if (Thread.currentThread().getName().equals("Thread-1")) {
                serviceA.process();
                serviceB.process();
                serviceC.process();
            } else if (Thread.currentThread().getName().equals("Thread-2")) {
                serviceB.process();
                serviceA.process();
                serviceC.process();
            } else {
                serviceC.process();
                serviceA.process();
                serviceB.process();
            }

            printLog();
            clearLog();
        }
    }
}
