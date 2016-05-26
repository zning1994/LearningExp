package exp34;

import java.io.*;

/**
 * Created by ZNing on 16/4/26.
 * 6. 以一下四种方式读取同一个大于 10M 的文件，
 * 1）直接使用 InputStream，按字节读，
 * 2）直接使用 InputStream，按字节数组读（数组大小为 1024），
 * 3）使用缓冲流读入内存后 按字节读，
 * 4）使用缓冲流读入内存后按字节数组读，数组大小为 1024.
 * 比较他们的执行时间。
 * http://www.weixueyuan.net/view/6045.html
 *
 */
public class prog4 {

    private static String fileName = "10MFile";

    public static void main(String[] args) throws Exception {
        File file = new File(fileName);
        // 1）直接使用 InputStream，按字节读
        InputStream inputStream1 = new FileInputStream(file);
        long begin1 = System.currentTimeMillis();
        while ((inputStream1.read()) != -1) ;
        long end1 = System.currentTimeMillis();
        System.out.println("1）直接使用 InputStream，按字节读的时间: " + (end1 - begin1) + " ms.");
        // 2）直接使用 InputStream，按字节数组读（数组大小为 1024）
        InputStream inputStream2 = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        long begin2 = System.currentTimeMillis();
        while ((inputStream2.read(bytes)) != -1) ;
        long end2 = System.currentTimeMillis();
        System.out.println("2）直接使用 InputStream，按字节数组读（数组大小为 1024）的时间: " + (end2 - begin2) + " ms.");
        // 3）使用缓冲流读入内存后 按字节读
        BufferedInputStream bos1 = new BufferedInputStream(new FileInputStream(fileName));
        long begin3 = System.currentTimeMillis();
        while ((bos1.read()) != -1) ;
        long end3 = System.currentTimeMillis();
        System.out.println("3）使用缓冲流读入内存后 按字节读的时间: " + (end3 - begin3) + " ms.");
        // 4）使用缓冲流读入内存后按字节数组读，数组大小为 1024
        BufferedInputStream bos2 = new BufferedInputStream(new FileInputStream(fileName));
        byte[] bytes2 = new byte[1024];
        long begin4 = System.currentTimeMillis();
        while ((bos2.read(bytes2)) != -1) ;
        long end4 = System.currentTimeMillis();
        System.out.println("4）使用缓冲流读入内存后按字节数组读，数组大小为 1024 的时间: " + (end4 - begin4) + " ms.");

    }

}
