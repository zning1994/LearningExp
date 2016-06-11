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
public class  program3 extends JFrame implements ItemListener{
    private JRadioButton[] lights = new JRadioButton[4];
    private String[] lightColor = {"红灯","黄灯","绿灯","熄灭所有灯"};
    private Color[] colors = {Color.red,Color.yellow,Color.green,Color.WHITE};
    private int n = 0;

    /**
     * 构造器
     */
    public program3(){

        //窗口构造
        this.setTitle("实验9：信号灯 By: ZNing");
        this.setSize(400, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        //按钮组构造
        ButtonGroup group = new ButtonGroup();
        for(int i = 0 ; i < lights.length; i++){
            lights[i] = new JRadioButton(lightColor[i],true);
            group.add(lights[i]);
            this.add(lights[i]);
            lights[i].addItemListener(this);
            lights[i].setBounds(3  + 100 * i, -31, 100, 100);
        }
    }

    /**
     * 重写：paint回调方法
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(n!=3){
            g.setColor(colors[n]);
            g.fillOval(160, 80 + n * 70, 60, 60);
        }
    }

    /**
     * main方法：设置窗口可见
     */
    public static void main(String[] args) {
        new program3().setVisible(true);
    }

    /**
     * 方法：监听按钮改变
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object obj = e.getSource();
        if(obj == lights[0]){
            n = 0;
        }else if(obj == lights[1]){
            n = 1;
        }else if(obj == lights[2]){
            n = 2;
        }else if(obj == lights[3]){
            n = 3;
        }
        repaint();
    }
}


