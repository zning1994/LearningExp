package exp34;

import java.io.*;

/**
 * Created by ZNing on 16/5/25.
 * 1. 写一个程序，接受用户的键盘输入，并把它写到文件中去。用户输入 exit 保存文件并退出程序。
 *
 */
public class prog1 {

    public static String fileName="hello.txt";

    public static void main(String[] args) throws Exception{
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName,true));//去掉true可以覆盖读写文件
        String str;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");

        do {
            str = br.readLine();
            if(!str.equals("exit")){
                out.write(str);
                out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
            }
        } while(!str.equals("exit"));
        out.close();
    }
}
