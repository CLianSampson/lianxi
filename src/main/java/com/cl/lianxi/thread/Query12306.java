package com.cl.lianxi.thread;


import java.util.concurrent.*;

public class Query12306 {

    private ThreadPoolExecutor pool;


    public  void aqsTest(){

        int corePoolSize = 4;
        int maxPoolSize = 6;
        long keepAliveTime = 60;
        TimeUnit unit = TimeUnit.SECONDS;

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20);

        pool = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,unit,queue);


        for (int i = 0 ; i<20 ; i++){
            MyThread task = new MyThread();
            task.setIp("" + i);

            pool.execute(task);
        }
    }




    private  class MyThread implements Runnable{

        private String ip = null;


        @Override
        public void run() {

            test();

        }


        private void test(){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ip is : " + ip);

            //线程安全，内部由阻塞队列维持线程安全
            pool.execute(this);

        }


        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }


    public static void main(String[] args) {
        Query12306 threadTest = new Query12306();
        threadTest.aqsTest();
    }

}
