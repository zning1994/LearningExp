package exp12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by ZNing on 2016-4-11.
 * 1. 编写程序，使界面中包含三个标签，背景分别为红黄蓝三色
 *
 */

public class program1 extends JFrame{

    //定义一个Panel和三个标签

    JPanel jpanel = null;
    JLabel jlb1 = null;
    JLabel jlb2 = null;
    JLabel jlb3 = null;

    public program1(){
        //创建对象
        jpanel = new JPanel();
        jlb1 = new JLabel("我是第一个RED标签");
        jlb2 = new JLabel("我是第二个YELLOW标签");
        jlb3 = new JLabel("我是第三个BLUE标签");

        //设置JLabel为不透明，后设置背景颜色(还设置了某两个标签文字的字体颜色)
        jlb1.setOpaque(true);
        jlb2.setOpaque(true);
        jlb3.setOpaque(true);
        jlb1.setForeground(Color.WHITE);
        jlb3.setForeground(Color.WHITE);
        jlb1.setBackground(Color.RED);
        jlb2.setBackground(Color.YELLOW);
        jlb3.setBackground(Color.BLUE);

        //让每个标签都显示出边框出来
        jlb1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //设置网格显示布局
        jpanel.setLayout(new GridLayout(3, 1));

        //将标签添加到panel
        jpanel.add(jlb1);
        jpanel.add(jlb2);
        jpanel.add(jlb3);

        //将panel添加到frame里面去
        this.add(jpanel);
    }

    public static void main(String[] args) {
        JFrame frame = new program1();
        frame.setTitle("实验1：标签颜色显示  By: ZNing");
        frame.setSize(250, 200);
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


