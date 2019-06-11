package com.hai.interview.threadstudy;

//java.util.concurrent
import com.alibaba.druid.pool.DruidDataSource;

import java.util.concurrent.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent. ScheduledThreadPoolExecutor;

/**
 *
 * 创建线程池的几种方法
 *  比如问你线程池的优点，so easy! 准备到了！ 然后问你ExecutorService 是主要的实现类，其中常用的有哪些？
 * Executors.newSingleThreadPool(),newFixedThreadPool(),newcachedTheadPool(),newScheduledThreadPool()。
 * 都是干啥的，特点是啥回答完了，好开心的说。
 * 当然这样没啥问题，但是同学你没有超出期待，你和其他同学比，你的优势在哪里？？
 * 都回答了这么多了还想怎样？？难道这样还不行？
 * 对你可以做的更多，更好，what????
 * 如果你看过《阿里巴巴Java开发手册》，请注意这一段：
 *
 * 【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样
 * 的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明:Executors 返回的线程池对象的弊端如下:
 * 1)FixedThreadPool 和 SingleThreadPool:
 *允许的请求队列长度为 Integer.MAX_VALUE(Integer的最大值2147483647)，可能会堆积大量的请求，从而导致 OOM。
 * (什么是OOM？ OOM，全称“Out Of Memory”，翻译成中文就是“内存用完了”，来源于java.lang.OutOfMemoryError。)
 *2)CachedThreadPool 和 ScheduledThreadPool(ScheduledThreadPoolExecutor):
 *允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。                                                                                                                                              参见《阿里巴巴Java开发手册》
 */
//参考：https://www.cnblogs.com/dolphin0520/p/3932921.html
public class ThreadPoolStudy {
    public static void main(String[] args) {
        //指定3个长度的工作队列
        LinkedBlockingDeque<Runnable> workQueue=new LinkedBlockingDeque<Runnable>(3);
        //修改线程池的策略
        // RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        //指定线程池参数：核心线程数，线程池最大线程数量，活跃时间，工作队列
        ThreadPoolExecutor threadPoolExecutor=
                    new ThreadPoolExecutor(
                            4, 7, 90, TimeUnit.SECONDS, workQueue
                    ,new ThreadPoolExecutor.CallerRunsPolicy());
            for (int i = 0; i < 15; i++) {
                threadPoolExecutor.execute(new Thread(new TestThreadPool(), "线程:".concat(i+"")));
                System.out.println("线程池中活跃线程数"+threadPoolExecutor.getActiveCount());
                if(workQueue.size()>0){
                    System.out.println("被阻塞的线程数为："+workQueue.size());
                }

            }
            threadPoolExecutor.shutdown();



    }
    public static class TestThreadPool implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}














