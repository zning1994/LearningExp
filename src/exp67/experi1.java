package exp67;

import java.util.Random;

/**
 * Created by ZNing on 2016-6-1.
 * 1. 用 Thread子类和Runnable实现类的方法编写多线程类 ，线程中循环100 次，每次循环中线程休眠10 秒内的任意时间，休眠完成后打印出线程名称、休眠时间和第几次执行。
 *
 */
//使用Thread类来撰写的多线程
class SubThread extends Thread{

    private String Name = null;

    public SubThread(String n){
        this.Name = n;
    }

    public void run(){
        for(int i=0; i<100; i++){
            int max=10;
            int min=1;
            Random random = new Random();
            long s = (long)(random.nextInt(max) % (max - min + 1) + min);
            System.out.println("[Using Thread Class] Thread Name: "+Name+" Sleep Time: "+s+" Running Time(s): "+(i+1) );
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

//使用Runnable接口来撰写的多线程
class RunThread implements Runnable{
    private String Name = null;

    public RunThread(String n){
        this.Name = n;
    }

    public void run(){
        for(int i=0; i<100; i++){
            int max=10;
            int min=1;
            Random random = new Random();
            long s = (long)(random.nextInt(max) % (max - min + 1) + min);
            System.out.println("[Using Runnable Interface] Thread Name: "+Name+" Sleep Time: "+s+" Running Times: "+(i+1) );
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class experi1 {
    public static void main(String[] args) {
        SubThread th1 = new SubThread("线程1");
        SubThread th2 = new SubThread("线程2");
        RunThread th3 = new RunThread("线程3");
        RunThread th4 = new RunThread("线程4");
        th1.start();
        th2.start();
        new Thread(th3).start();
        new Thread(th4).start();
    }
}
