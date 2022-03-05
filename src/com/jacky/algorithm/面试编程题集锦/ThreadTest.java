package com.jacky.algorithm.面试编程题集锦;

public class ThreadTest {

    private static int count = 0;

    private static final Object lock = new Object();

    static class PrintRunner implements Runnable{

        @Override
        public void run(){
            // odd
            while(count <= 100){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + " : " + count++);
                    lock.notify();
                    if(count <= 100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        new Thread(new PrintRunner(),"Thread even").start();
        new Thread(new PrintRunner(),"Thread odd").start();
    }
}
