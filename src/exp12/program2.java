package exp12;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ZNing on 2016-5-25.
 * 2. 编写程序界面中包含一个标签，一个文本框和一个按钮。当用户单击按钮时，程序把文 本框中的内容复制到标签中
 */
public class program2 extends JFrame{

    //定义一个Panle和四个标签
    JPanel jpanel = null;
    JLabel jlb1 = null;
    JLabel jlb2 = null;
    JLabel jlb3 = null;
    JLabel jlb4 = null;

    public program2() {

        //创建对象，
        jpanel = new JPanel();
        jlb1 = new JLabel("Department of Computer Science");
        jlb2 = new JLabel("School of Computing");
        jlb3 = new JLabel("Armstrong Atlantic State University");
        jlb4 = new JLabel("Tel:(912)921-6440");

        //让每个标签都显示出边框出来
        jlb1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jlb2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jlb3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jlb4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        jpanel.setLayout(new GridLayout(4, 1));

        //将标签添加到panel
        jpanel.add(jlb1);
        jpanel.add(jlb2);
        jpanel.add(jlb3);
        jpanel.add(jlb4);

        //将panel添加到frame里面去
        this.add(jpanel);
    }

    public static void main(String[] args) {
        JFrame frame = new program2();
        frame.setTitle("显示标签");
        frame.setSize(250, 200);
        frame.setLocation(500, 200);
        frame.setVisible(true);
    }
}

