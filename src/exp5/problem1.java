package exp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ZNing on 2016-5-25.
 * 1、创建一个 URL 对象，完成如下要求：获取协议、主机名、端口号、文件路径。利用该 URL 的输入流读取资源文件并在控制台输出
 *
 */
public class problem1 {
    public static void main(String args[]){
        System.out.print("Hi");
        URL google = null;
        try {
            google = new URL("http://www.sdust.edu.cn:80/content__0C1B6CA56B3813CDA2D832ACC61BCC20.htm");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //获取相关信息
        String urlProtocol;
        urlProtocol = google.getProtocol();
        System.out.println("这个URL的协议名为："+urlProtocol);

        String urlHost;
        urlHost = google.getHost();
        System.out.println("这个URL的主机名为："+urlHost);

        Integer urlPort;
        urlPort = google.getPort();
        System.out.println("这个URL的端口号为："+urlPort);

        String urlPath;
        urlPath = google.getPath();
        System.out.println("这个URL的文件路径为："+urlPath);

        String urlFile;
        urlFile = google.getFile();
        System.out.println("这个URL的文件名为："+urlFile);

        String urlRef;
        urlRef = google.getRef();
        System.out.println("这个URL在文件中的相对位置为："+urlRef);

        String urlQuery;
        urlQuery = google.getQuery();
        System.out.println("这个URL的查询名为："+urlQuery);


        //获取页面信息

        BufferedReader in = null;
        String inputLine;
        try {
            in = new BufferedReader(new InputStreamReader(google.openStream()));
            System.out.println("这个URL的信息为：");
            while ((inputLine=in.readLine())!=null)
                System.out.println(inputLine);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
