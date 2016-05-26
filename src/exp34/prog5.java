package exp34;

import java.io.*;

/**
 * Created by ZNing on 16/4/26.
 * 7. 使用 GBK、UTF-8 等方式在文件中保存一段中文文字，并读出来，正确地打印到屏幕上。
 *
 */
public class prog5 {

    public static String fileName1="GBK.txt";
    public static String fileName2="UTF_8.txt";

    public static void main(String[] args) throws Exception{
        WriteGBK();
        WriteUTF_8();
        System.out.println("写入成功\n==========\n以下是正确的编码输出情况：");
        ReadFile(fileName1,"GBK");
        ReadFile(fileName2,"UTF-8");
        System.out.println("以下是错误的编码输出情况：(只演示了GBK错转UTF-8的情况)");
        ReadFile(fileName1,"UTF-8");
    }

    public static void WriteGBK() throws Exception{
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("GBK.txt"),"GBK");
        osw.write("山东科技大学信息科学与工程学院");
        osw.close();
    }

    public static void WriteUTF_8() throws Exception{
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("UTF_8.txt"),"UTF-8");
        osw.write("山东科技大学信息科学与工程学院");
        osw.close();
    }

    public static void ReadFile(String f, String charSet) throws Exception{
        InputStream is = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(is, charSet);
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        while((str = br.readLine()) != null){
            System.out.println(str);
        }
        br.close();
        isr.close();
        is.close();
    }
}
