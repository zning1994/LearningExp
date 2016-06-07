package exp12;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by ZNing on 2016-4-11.
 * 9. 信号灯。编写应用程序，在窗口的北面添加一个下拉框，其中有“红灯”、“黄灯”、“绿 灯”、“熄灭所有灯”四个选项，在窗口的中心添加一个画布，用户选择某个下拉选项后， 画布上有相应的信号灯或者清空屏幕上的所有图。
 *
 */
public class program3{

    //定义一个Panle和一个标签
    private JPanel jpanel = null;
    private  MyFrame jFrame = null;
    private  JComboBox jComboBox = null;
    private Graphics g;


    public void init(){
        jFrame = new MyFrame("实验9：信号灯 By: ZNing");
        jpanel = new JPanel();
        jComboBox = new JComboBox();

        jComboBox.addItem("红灯");
        jComboBox.addItem("黄灯");
        jComboBox.addItem("绿灯");
        jComboBox.addItem("熄灭所有灯");

        jpanel.add(jComboBox);

        //jFrame.add(jpanel.BorderLayout.SOUTH);

        jFrame.setSize(600, 500);
        jFrame.setSize(500, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }


    public void event(){
        MyItemListener listener = new MyItemListener();
        jComboBox.addItemListener(listener);
    }

    public program3(){
            init();
            event();
    }

    public static void main(String[] args) {
        //JFrame frame = new program3();
        SignalLamp s1 = new SignalLamp();
    }

    class MyFrame extends  JFrame{
        Color c;
        public void paint(Graphics g){
            super.paint(g);
            c=g.getColor();
        }

        public void enableRed(Graphics g){
            g.setColor(c);
            paint(g);
            g.setColor(Color.RED);
            g.fillOval(130,90,33,33);
            g.setColor(c);
        }

        public void enableYellow(Graphics g){
            g.setColor(c);
            paint(g);
            g.setColor(Color.YELLOW);
            g.fillOval(130,130,33,33);
            g.setColor(c);
        }

        public void enableGreen(Graphics g){
            g.setColor(c);
            paint(g);
            g.setColor(Color.GREEN);
            g.fillOval(130,170,33,33);
            g.setColor(c);
        }

        public void disableAll(Graphics g){
            g.setColor(c);
            paint(g);
            g.setColor(Color.BLUE);
            g.fillOval(130,170,33,33);
            g.setColor(c);
        }

        public MyFrame(String name){
            super(name);
        }
    }

    //监听器的实现类
    class MyItemListener implements ItemListener
    {

        public void itemStateChanged(ItemEvent e) {
            if(e.getItem().equals("红灯"))	//若按下的是红色的按钮
            {
                jFrame.enableRed(g);
            }
            if(e.getItem().equals("黄灯"))
            {
                jFrame.enableYellow(g);
            }
            if(e.getItem().equals("绿灯"))
            {
                jFrame.enableGreen(g);
            }
            if(e.getItem().equals("熄灭所有灯"))
            {
                jFrame.disableAll(g);
            }
        }
    }

}


