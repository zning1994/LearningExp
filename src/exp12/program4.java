package exp12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ZNing on 2016-4-11.
 * 10. 实现一个计算器，界面包括 10 个数字按钮和 4 个运算符（+ - * /）按钮，以及等号和清 空两个辅助按钮。还有一个用于显示输入和输出的文本框。运算过程显示到文本框中。 可用 GridLayout 实现。
 *
 */
public class program4 implements ActionListener{
    /**用来标识所有按键*/
    final String[] KEYS = { "CE", "C", "←", "÷", "7", "8", "9", "×", "4","5", "6", "-", "1", "2", "3", "+", "0", ".", "=" };
    /**将所有按钮用Button数组实现*/
    JButton[] keys = new JButton[KEYS.length];
    /**用来显示结果区域*/
    JTextField resultText = new JTextField("0");
    /**标志按的是表达式第一个数字，还是运算符后的第一个数字*/
    private boolean firstDigit = true;
    /**中间结果*/
    private double resultNum = 0.0;
    /**当前运算的运算符  */
    private String operator = "=";
    /**判断操作是否合法*/
    private boolean operateValidFlag = true;

    /**
     * 向面板中添加组件
     * @param pane 用来添加组件的面板
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout layout = new GridBagLayout();
        pane.setLayout(layout);
        resultText.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
        resultText.setEditable(false);
        resultText.setHorizontalAlignment(SwingConstants.RIGHT);
        pane.add(resultText,new GBC(0, 0, 4, 1).setIpad(400, 50).setWeight(0.5, 0.5).setFill(GridBagConstraints.BOTH));
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new JButton(KEYS[i]);
            if(i == keys.length-3){
                pane.add(keys[i],new GBC(i % 4, i / 4 + 1,2,1).setIpad(0, 12).setInsets(1).setFill(GridBagConstraints.BOTH).setWeight(0.5, 0.5));
            }else if (i == keys.length-2 || i == keys.length-1) {
                pane.add(keys[i],new GBC(i % 4+1, i / 4 + 1).setIpad(0, 12).setInsets(1).setFill(GridBagConstraints.BOTH).setWeight(0.5, 0.5));
            }else {
                pane.add(keys[i], new GBC(i % 4, i / 4 + 1).setIpad(0, 12).setInsets(1).setFill(GridBagConstraints.BOTH).setWeight(0.5, 0.5));
            }

        }
        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this);
        }
    }

    /**
     * 创建和显示界面
     */
    public void createAndShowGUI() {
        JFrame frame = new JFrame("实验10:计算器 By: ZNing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * 执行点击按钮出发的动作
     * @override
     //* @see java.awt.event.actionPerformed
     */
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if(label.equals(KEYS[0])){
            resultText.setText("0");
        }else if (label.equals(KEYS[1])) {
            handleC();
        }else if (label.equals(KEYS[2])) {
            handleBackspace();
        }else if ("0123456789.".indexOf(label) >= 0) {
            handleNumber(label);
        }else{
            handleOperator(label);
        }
    }

    /**
     * 处理撤销建
     */
    private void handleBackspace() {
        String text = resultText.getText();
        int i = text.length();
        if (i > 0) {
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            } else {
                resultText.setText(text);
            }
        }
    }

    /**
     * 处理数字键
     * @param key 数字按键
     */
    private void handleNumber(String key) {
        if (firstDigit) {
            resultText.setText(key);
        } else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {
            resultText.setText(resultText.getText() + ".");
        } else if (!key.equals(".")) {
            resultText.setText(resultText.getText() + key);
        }
        firstDigit = false;
    }

    /**
     * 处理C建
     */
    private void handleC() {
        resultText.setText("0");
        firstDigit = true;
        operator = "=";
    }

    /**
     * 处理等号建
     * @param key 运算符按键
     */
    private void handleOperator(String key) {
        if (operator.equals("÷")) {
            if (getNumberFromText() == 0.0) {
                operateValidFlag = false;
                resultText.setText("除数不能为零");
            } else {
                resultNum /= getNumberFromText();
            }
        } else if (operator.equals("+")) {
            resultNum += getNumberFromText();
        } else if (operator.equals("-")) {
            resultNum -= getNumberFromText();
        } else if (operator.equals("×")) {
            resultNum *= getNumberFromText();
        }else if (operator.equals("=")) {
            resultNum = getNumberFromText();
        }
        if (operateValidFlag) {
            long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                resultText.setText(String.valueOf(t1));
            } else {
                resultText.setText(String.valueOf(resultNum));
            }
        }
        operator = key;
        firstDigit = true;
        operateValidFlag = true;
    }

    /**
     * 私有工具函数，从结果框中获取运算结果
     * @return 运算结果
     */
    private double getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException e) {
        }
        return result;
    }


    /**
     * 程序的入口
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new program4().createAndShowGUI();
            }
        });
    }
}
