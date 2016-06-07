package exp12;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by ZNing on 2016-5-29.
 */

public class Test3 {
    private JPanel jp;
    private MyJFrame jf;
    private Checkbox cb1,cb2,cb3;
    private Graphics g;

    //初始化界面
    public void init(){
        jf=new MyJFrame("交通信号灯");
        jp=new JPanel();

        //设置jf的基本属性
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(200, 200, 300,300);
        jf.setLayout(new BorderLayout());
        jf.setVisible(true);	//先设为true后graphic才不会发生空指针异常

        //创建三个复选框
        cb1=new Checkbox("Red");
        cb2=new Checkbox("Yellow");
        cb3=new Checkbox("Green");

        //将三个复选框添加到面板中
        jp.add(cb1);
        jp.add(cb2);
        jp.add(cb3);

        //将面板添加到窗体中,位于窗体的底端
        jf.add(jp,BorderLayout.SOUTH);
        //获取窗体的画笔
        g=jf.getGraphics();
    }

    public void event(){
        MyItemListener listener=new MyItemListener();	//创建监听器对象实例
        cb1.addItemListener(listener);					//为三个复选框添加监听器
        cb2.addItemListener(listener);
        cb3.addItemListener(listener);
    }

    //构造器
    public Test3(){
        init();
        event();
    }


    public static void main(String[] args) {
        SignalLamp sl=new SignalLamp();
    }

    class MyJFrame extends JFrame
    {
        Color c;	//用于获取开始时Graphics的颜色
        //该方法用于画出交通灯的模型
        public void paint(Graphics g)
        {
            //调用父类的方法画这个界面原来本身有的东西
            //如果不调的话这个界面中原来本身有的东西都没有了
            super.paint(g);
            c=g.getColor();
            //画出交通灯的原型，未填充颜色
            g.drawRect(120, 80, 50, 140);
            g.drawOval(130, 90, 30, 30);
            g.drawOval(130, 130, 30, 30);
            g.drawOval(130, 170, 30, 30);
        }

        //亮红灯
        public void lightRed(Graphics g)
        {
            g.setColor(c);			//设置画笔Graphics一开始的颜色
            paint(g);				//调用paint方法画出交通灯模型
            g.setColor(Color.red);	//设置画笔Graphics的颜色为红色
            g.fillOval(130, 90, 33, 33);	//画出带有颜色的实心圆，传入坐标半径等参数
            g.setColor(c);					//将画笔的颜色设为原先的颜色
        }
        //亮黄灯
        public void lightYellow(Graphics g)
        {
            g.setColor(c);
            paint(g);
            g.setColor(Color.yellow);
            g.fillOval(130-2, 130-2, 34, 34);
            g.setColor(c);
        }
        //亮绿灯
        public void lightGreen(Graphics g)
        {
            g.setColor(c);
            paint(g);
            g.setColor(Color.green);
            g.fillOval(130-2, 170-2, 34, 34);
            g.setColor(c);
        }
        //构造器
        public MyJFrame(String name){
            super(name);
        }
    }

    //监听器的实现类
    class MyItemListener implements ItemListener
    {

        public void itemStateChanged(ItemEvent e) {
            if(e.getItem().equals("Red"))	//若按下的是红色的按钮
            {
                if(cb1.getState()){
                    cb2.setState(false);		//将另外两个复选框的选中设置为为选中，并调用MyJFrame
                    cb3.setState(false);		//的方法使得红色的灯亮起来
                    jf.lightRed(g);
                }
                else
                    jf.paint(g);
            }
            if(e.getItem().equals("Yellow"))
            {
                if(cb2.getState()){
                    cb1.setState(false);
                    cb3.setState(false);
                    jf.lightYellow(g);
                }
                else
                    jf.paint(g);
            }
            if(e.getItem().equals("Green"))
            {
                if(cb3.getState()){
                    cb1.setState(false);
                    cb2.setState(false);
                    jf.lightGreen(g);
                }
                else
                    jf.paint(g);
            }
        }
    }
}
