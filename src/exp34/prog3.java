package exp34;

import java.io.*;

/**
 * Created by ZNing on 16/4/26.
 * 5. 把 1~1000000 的数字写入文件中，一种方式以字符串的形式写入，另一种方式直接写入数字的二进制表示，比较文件的大小。
 *
 */
public class prog3 {

    public static String fileName1="exp3one.txt";
    public static String fileName2="exp3two.txt";

    public static void main(String[] args) throws Exception{
        // 写入字符串文件
        //FileWriterwriter = new FileWriter(fileName);
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName1,true));//去掉true可以覆盖读写文件
        for (Integer i=1;i<=1000000;i++)
            out.write(i.toString());
        out.close();

        //写入二进制文件
        DataOutputStream out2 = new DataOutputStream(new FileOutputStream(fileName2));
        for (int i=1;i<=1000000;i++)
            out2.writeInt(i);
        out2.close();

        //获取文件大小并显示文件大小
        File file1 = new File(fileName1);
        System.out.println(file1.getName() + "的文件大小是：" + file1.length() + "Bytes.");
        File file2 = new File(fileName2);
        System.out.println(file2.getName() + "的文件大小是：" + file2.length() + "Bytes.");

    }
}
