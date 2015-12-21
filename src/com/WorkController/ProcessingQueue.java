package com.WorkController;

import org.apache.log4j.Logger;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程工作队列
 */
public class ProcessingQueue {

    Logger logger=Logger.getLogger(ProcessingQueue.class);

    public static ProcessingQueue getQueueContext(){

        return ProcessingQueueHolder.instance;
    }

    private static ThreadPoolExecutor ThreadPool=null;


    public  ProcessingQueue() {
        if(ThreadPool==null)
        {
            //核心线程为5，最大10个线程
            LinkedBlockingDeque<Runnable> workingQueue=new LinkedBlockingDeque<Runnable>();
            ThreadPool= new ThreadPoolExecutor(5,10,3, TimeUnit.SECONDS,workingQueue ,new ThreadPoolExecutor.AbortPolicy());
        }
    }

    //文本处理任务提交到工作队列
    public void Submit(Runnable characterProcessor)
    {
        ThreadPool.execute(characterProcessor);
    }

    private static class ProcessingQueueHolder {
        static final ProcessingQueue instance = new ProcessingQueue();
    }
}
