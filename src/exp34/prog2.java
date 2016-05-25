package exp34;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ZNing on 16/4/26.
 * 3. 写一个程序来显示命令行指定目录中所有文件数目、目录数目、所有文件占用的空间大小。
 *
 */
public class prog2 {
    public static void main(String[] args) {
        String str = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter file path of text:");
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long begin00 = System.currentTimeMillis();
        int fileCount = 0, folderCount = 0;
        long length = 0;
        File[] list = new File(str).listFiles();
        for (File file : list) {
            if (file.isFile()) {
                fileCount++;
                length += file.length();
            } else {
                folderCount++;
            }
        }
        System.out.println("files:\t" + fileCount);
        System.out.println("folders:\t" + folderCount + "\nCurrent Folder total:\t"+ length +" Bytes");
        System.out.println("---------------");
        System.out.println("It takes "+ (System.currentTimeMillis() - begin00) + " ms.");
    }


}
