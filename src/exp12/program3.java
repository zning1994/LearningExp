package exp12;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

/**
 * Created by ZNing on 2016-4-11.
 * 9. 信号灯。编写应用程序，在窗口的北面添加一个下拉框，其中有“红灯”、“黄灯”、“绿 灯”、“熄灭所有灯”四个选项，在窗口的中心添加一个画布，用户选择某个下拉选项后， 画布上有相应的信号灯或者清空屏幕上的所有图。
 *
 */
public class program3 extends JFrame{

    //定义一个Panle和一个标签
    JPanel jpanel = new JPanel();
    JComboBox jComboBox = new JComboBox();

//    public program3(){
//        //创建对象，
//        jpanel = new JPanel();
//        jComboBox = new JComboBox();
//        g.setColor(Color.RED);
//        g.fillOval(200,100,40,40);
//
//        jComboBox.addItem("红灯");
//        jComboBox.addItem("黄灯");
//        jComboBox.addItem("绿灯");
//        jComboBox.addItem("熄灭所有灯");
//
//        //将标签添加到panel
//        jpanel.add(jComboBox);
//
//        //将panel添加到frame里面去
//        this.add(jpanel);
//    }

    public void paintRedCircle2(Graphics g){
        this.setVisible(true);
        g.setColor(Color.RED);
        g.fillOval(80,50,20,20);
        this.setBounds(100,200,500,500);
        this.setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        JFrame frame = new program3();
        //this.jComboBox.addItem("红灯");
        //this.jComboBox.addItem("黄灯");
        //this.jComboBox.addItem("绿灯");
        //this.jComboBox.addItem("熄灭所有灯");
        //将标签添加到panel
        //this.jpanel.add(this.jComboBox);

        //将panel添加到frame里面去
        //this.add(this.jpanel);
        frame.setTitle("实验3：信号灯  By: ZNing");
        frame.setSize(250, 500);
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}

//class DrawPanel extends  JPanel{
//    public void paintRedCircle(Graphics g){
//        this.setVisible(true);
//        super.printComponents(g);
//        Graphics2D g2 = (Graphics2D) g;
//        double centerX = 50;
//        double centerY = 80;
//        double radius = 10;
//        Ellipse2D circle = new Ellipse2D.Double();
//        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY +radius);
//        g2.setPaint(Color.RED);
//        g2.fill(circle);
//    }
//}
