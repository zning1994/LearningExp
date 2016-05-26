package exp12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by ZNing on 2016-4-11.
 * 1. 编写程序，使界面中包含三个标签，背景分别为红黄蓝三色
 *
 */

public class oldprogram1 {
    public static void main(String[] args) {
        ToolBarFrame frame = new ToolBarFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * A frame with a toolbar and menu for color changes.
 */
class ToolBarFrame extends JFrame {
    public ToolBarFrame() {
        setTitle("实验1：标签换色");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocation(300,200);

        // add a panel for color change
        panel = new JPanel();
        add(panel, BorderLayout.CENTER);

        JLabel jlb1 = new JLabel("蓝色");
        JLabel jlb2 = new JLabel("黄色");
        JLabel jlb3 = new JLabel("红色");

        // set up actions
        Action blueAction = new ColorAction("蓝色", new ImageIcon(
                "./src/exp12/blue-ball.gif"), Color.BLUE);
        Action yellowAction = new ColorAction("黄色", new ImageIcon(
                "./src/exp12/yellow-ball.gif"), Color.YELLOW);
        Action redAction = new ColorAction("红色", new ImageIcon(
                "./src/exp12/red-ball.gif"), Color.RED);
        Action exitAction = new AbstractAction("退出", new ImageIcon(
                "./src/exp12/exit.gif")) {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };
        exitAction.putValue(Action.SHORT_DESCRIPTION, "退出");

        //让每个标签都显示出边框出来
        jlb1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jlb2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jlb3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //将标签添加到panel
        panel.add(jlb1);
        panel.add(jlb2);
        panel.add(jlb3);

        // populate menu
        JMenu menu = new JMenu("颜色切换");
        menu.add(yellowAction);
        menu.add(blueAction);
        menu.add(redAction);
        menu.add(exitAction);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 600;

    private JPanel panel;

    /**
     * The color action sets the background of the frame to a given color.
     */
    class ColorAction extends AbstractAction {
        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, name + " background");
            putValue("Color", c);
        }

        public void actionPerformed(ActionEvent event) {
            Color c = (Color) getValue("Color");
            panel.setBackground(c);
        }
    }
}
