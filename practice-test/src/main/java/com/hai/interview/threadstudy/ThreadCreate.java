package com.hai.interview.threadstudy;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCreate {
    public static void main(String[] args) {

        //创建并启动线程1
        new Thread01().start();
        //创建并启动线程2
        new Thread(new Thread02()).start();
        //创建并启动线程3、
        FutureTask<String> futureTask = new FutureTask<String>(new Thread03());
        new Thread(futureTask).start();
        try {
            String str = futureTask.get();
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
//Thread类实现了Runnabel接口
class Thread01 extends Thread{
    public void  run(){
        //重写run方法，需要执行的代码
        System.out.println("----线程1----");
    }

}
//特点：可以突破继承的局限性（接口可以多实现）
class Thread02 implements Runnable{
    //实现Runnable接口的类需要再次用Thread类包装后才能调用start方法。（三个Thread对象包装一个类对象，就实现了资源共享）。
    // 即new Thread(new Thread02()).start();//创建并启动线程
    @Override
    public void run() {
        //重写run()方法，需要执行的业务
        System.out.println("----线程2----");
    }
}

/**
 * 三、通过Callable和Future创建线程(特点：有返回值且可以抛出异常)
 * （1）创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值。
 * （2）创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。
 * （3）使用FutureTask对象作为Thread对象的target创建并启动新线程。
 * （4）调用FutureTask对象的get()方法来获得子线程执行结束后的返回值
 */
class Thread03 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "----线程3----";
    }
}

/*
 * 创建线程的几种方式
 * ①继承Thread类（真正意义上的线程类），是Runnable接口的实现。
 * ②实现Runnable接口，并重写里面的run方法。
 * ③使用Executor框架创建线程池。Executor框架是juc里提供的线程池的实现。
 * 调用线程的start()：启动此线程；调用相应的run()方法
 * 继承于Thread类的线程类，可以直接调用start方法启动线程（使用static也可以实现资源共享）.
 * 一个线程（对象）只能够执行一次start()，而且不能通过Thread实现类对象的run()去启动一个线程。
 * 实现Runnable接口的类需要再次用Thread类包装后才能调用start方法。（三个Thread对象包装一个类对象，就实现了资源共享）。
 *      线程的使用的话，注意锁和同步的使用。（多线程访问共享资源容易出现线程安全问题）
 *       一般情况下，常见的是第二种。
 *          Runnable接口有如下好处：
 *         ①避免点继承的局限，一个类可以继承多个接口。
 *         ②适合于资源的共享
 *Thread的常用方法：
 * 1.start()：启动线程并执行相应的run()方法
 * 2.run():子线程要执行的代码放入run()方法中
 * 3.currentThread()：静态的，调取当前的线程
 * 4.getName():获取此线程的名字
 * 5.setName():设置此线程的名字
 * 6.yield():调用此方法的线程释放当前CPU的执行权（很可能自己再次抢到资源）
 * 7.join():在A线程中调用B线程的join()方法，表示：当执行到此方法，A线程停止执行，直至B线程执行完毕，
 * A线程再接着join()之后的代码执行
 * 8.isAlive():判断当前线程是否还存活
 * 9.sleep(long l):显式的让当前线程睡眠l毫秒  (只能捕获异常，因为父类run方法没有抛异常)
 * 10.线程通信（方法在Object类中）：wait()   notify()  notifyAll()
 *
 * 设置线程的优先级（非绝对，只是相对几率大些）
 * getPriority()：返回线程优先值
 * setPriority(int newPriority)：改变线程的优先级
 */


