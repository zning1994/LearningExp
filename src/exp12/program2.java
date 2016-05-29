package exp12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ZNing on 2016-5-25.
 * 2. 编写程序界面中包含一个标签，一个文本框和一个按钮。当用户单击按钮时，程序把文 本框中的内容复制到标签中
 */
public class program2 extends JFrame implements ActionListener {

    //定义一个Panle和一个标签
    JPanel jpanel = null;
    JLabel jlb1 = null;
    JButton jButton = null;
    JTextField jTextField = null;
    public program2() {

        //创建对象，
        jpanel = new JPanel();
        jlb1 = new JLabel("我是一个标签，来改我啊");
        jButton = new JButton("提交");
        jTextField = new JTextField();

        //让每个标签都显示出边框出来
        jlb1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //设置网格显示布局
        jpanel.setLayout(new GridLayout(3, 1));

        //给JButton添加事件

        jButton.addActionListener(this);

        //将标签添加到panel
        jpanel.add(jlb1);
        jpanel.add(jButton);
        jpanel.add(jTextField);
        jpanel.add(jButton);

        //将panel添加到frame里面去
        this.add(jpanel);
    }

    public static void main(String[] args) {
        JFrame frame = new program2();
        frame.setTitle("实验2：显示与修改标签  By: ZNing");
        frame.setSize(250, 200);
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jlb1.setText(jTextField.getText());

    }
}

