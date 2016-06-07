package exp12;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ZNing on 2016-5-29.
 */
public class Test2 extends JFrame{
    public void paint(Graphics g) {
        this.setVisible(true);
        g.setColor(Color.red);// 将画笔放在图形前面，否则会默认为黑色，那么颜色设置无效
        g.fillOval(200, 100, 40, 40);// 圆
        //g.fillOval(100, 100, 50, 100);// 椭圆，半径不同
        //g.drawRect(100, 300, 390, 100);// 矩形
        //this.setBounds(100, 200, 500, 500);
        //this.setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {

        JFrame frame = new Test2();// 你妹你发现没，这里如果不调用setVisible方法，窗口显示不出来啊
        frame.setSize(250, 500);
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame .setVisible(true);

    }
}
