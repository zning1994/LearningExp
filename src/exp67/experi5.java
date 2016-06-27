package exp67;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by ZNing on 16/6/27.
 *
 * 7. 试用多线程的方法编写一个读文件序，写时入0~10000的整形数据(非字符串)
 * 允许多个使用者同时读文件 ，一个使用者写文件，读写不能同时进行。（提示：
 * 设置一个布尔对象标识读、写文件过程全程同步，读文件过程仅需对布尔对象操作同步，
 * 利用线程协调机制wait-notify和布尔对象共同协调不能同时读写的过程 ）
 */

public class experi5 {
    public static void main(String[] args) {
        testWriterReader();
    }

    public static void testWriterReader() {
        TheCubbyHole db = new TheCubbyHole();// 缓冲区
        TheReader r1 = new TheReader(1, db);// 读者
        TheReader r2 = new TheReader(2, db);
        TheWriter w1 = new TheWriter(1, db);// 写者
        TheReader r3 = new TheReader(3, db);
        TheReader r4 = new TheReader(4, db);
        TheWriter w2 = new TheWriter(2, db);

        r1.start();
        r2.start();
        r3.start();
        w1.start();
        r4.start();
        w2.start();
    }
}

/**
 *
 * @类说明 ：缓冲区
 */
class TheCubbyHole {
    private int readerCount;// 读者数
    private int writerCount;// 写者数
    private boolean dbReading;// 读信号量
    private boolean dbWriting;// 写信号量

    public TheCubbyHole() {
        readerCount = 0;
        writerCount = 0;
        dbReading = false;
        dbWriting = false;
    }

    public static void napping() {// 线程睡眠，不消耗CPU资源
        try {
            Thread.sleep((int) (Math.random() * 4000));
        } catch (Exception e) {
        }
    }

    public synchronized int startRead() {// 开始读
        ++readerCount;
        while (writerCount > 0) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("Read-Write.txt"));
                System.out.println("Reader is waiting");
                wait();// 等待写者发出notify
            } catch (Exception e) {
            }
        }
        if (readerCount >= 1) {
            dbReading = true;
        }
        return readerCount;
    }

    public synchronized int endReading() {// 结束读
        --readerCount;
        if (readerCount == 0) {
            dbReading = false;
        }
        notifyAll();
        System.out.println("----------------------------------one reader is done reading. Count=" + readerCount);
        return readerCount;
    }

    public synchronized void startWriting() {// 开始写

        while (dbReading == true || dbWriting == true) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("Read-Write.txt",true));
                for (int i=0;i<10000;i++)
                {
                    out.write(i);
                    out.newLine();
                }
                out.close();
                wait();// 等待读者发出notify
            } catch (Exception e) {
            }

        }
        ++writerCount;
        dbWriting = true;
    }

    public synchronized void endWriting() {// 结束写
        --writerCount;
        dbWriting = false;
        System.out.println("----------------------------------one writer is done writing. Count=" + writerCount);

        notifyAll();
    }

}

/**
 *
 * @类说明 ：读者类
 */
class TheReader extends Thread {// 定义读线程，继承Thread类，重写run方法

    private TheCubbyHole C;
    private int readerNum;

    public TheReader(int r, TheCubbyHole db) {
        readerNum = r;
        C = db;
    }

    public void run() {
        int c;
        //while (true) {
        c = C.startRead();
        System.out.println("reader " + readerNum + " is reading. Count=" + c);

        //CubbyHole.napping();
        c = C.endReading();
        //}
    }
}

/**
 *
 * @类说明 ：写者类
 */
class TheWriter extends Thread {// 定义写线程
    private TheCubbyHole C;
    private int writerNum;

    public TheWriter(int w, TheCubbyHole db) {
        writerNum = w;
        C = db;

    }

    public void run() {
        //while (true) {

        C.startWriting();
        System.out.println("Writer " + writerNum + " is writing");

        //CubbyHole.napping();
        C.endWriting();
        //}
    }
}

