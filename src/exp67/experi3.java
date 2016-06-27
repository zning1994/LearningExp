package exp67;
import java.awt.*;
import javax.swing.*;

/**
 * Created by ZNing on 16/6/27.
 * 5. 你追我赶。在一个图形界面上构造两个位于同一起跑线方块，
 * 起跑线位于界面靠左置，A方块先开始运动，向右移动50像素后停止，
 * B方块开始运动，向右移动100像素后停止，A方块继续向右运动100像素后停止，
 * B方块开始运动，如此循环接替执行，直至某一个方块到达终点，界面显示该方块胜利信息。
 */
public class experi3 extends JFrame {
    // 全局变量
    static int positionA = 50, positionB = 50, distanceAll = 800;
    static int RecWidth = 50, RecHeight = 50;
    static char winner;
    static long sleeptime = 300;
    static boolean waitA = true, waitB = true, gaming = true, unrepaint = true;

    //构造函数
    public experi3() {
        setTitle("实验5：你追我赶  By: ZNing");
        setBackground(Color.WHITE);
        setSize(800, 500);
        setLocation(100, 200);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //绘图函数
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        g.clearRect(0, 0, 800, 400);
        g.setColor(Color.RED);
        g.fillRect(positionA - 50, 100, RecWidth, RecHeight);
        g.setColor(Color.BLUE);
        g.fillRect(positionB - 50, 300, RecWidth, RecHeight);

        if (!gaming) {
            g.setFont(new Font("宋体", ALLBITS, 50));
            if (winner == 'A') {
                g.setColor(Color.RED);
                g.drawString(new String("红方胜"), 150, 150);
            } else if (winner == 'B') {
                g.setColor(Color.BLUE);
                g.drawString(new String("蓝方胜"), 150, 150);
            }
        }
    }

    public static void main(String[] args) {
        waitA = false;
        waitB = true;
        unrepaint = false;

        threadframe tf = new threadframe();
        threadA tA = new threadA();
        threadB tB = new threadB();

        tf.start();
        tA.start();
        tB.start();

        try {
            tf.join();
            tA.join();
            tB.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return;
    }


    //红色方块线程
    public static class threadA extends Thread {

        public void run() {
            while (gaming) {

                while (waitA) {
                    if (!gaming)return;
                    System.out.print("");
                }

                try {
                    sleep(sleeptime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                int distance = (int) (Math.random() * 1000) % 100;
                positionA += distance;
                if (positionA >= distanceAll) {
                    positionA = distanceAll;
                    unrepaint = false;
                    gaming = false;
                    winner = 'A';
                }
                unrepaint = false;
                waitA = true;
                waitB = false;
            }
        }
    }

    //蓝色方块线程
    public static class threadB extends Thread {

        public void run() {
            while (gaming) {

                while (waitB) {
                    if (!gaming)return;
                    System.out.print("");
                }

                try {
                    sleep(sleeptime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                int distance = (int) (Math.random() * 1000000) % 100;
                positionB += distance;

                if (positionB >= distanceAll) {
                    positionB = distanceAll;
                    unrepaint = false;
                    gaming = false;
                    winner = 'B';
                }
                unrepaint = false;
                waitB = true;
                waitA = false;
            }
        }
    }

    //框架刷新线程
    public static class threadframe extends Thread {
        experi3 jiemian = new experi3();

        public void run() {
            while (gaming) {
                while (unrepaint) {
                    if (!gaming)return;
                    System.out.print("");
                }
                jiemian.repaint();
                unrepaint = true;
            }
        }
    }

}

