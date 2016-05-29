package exp5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by ZNing on 2016-5-29.
 * 4. 编写程序，利用数据包（Datagram）将一个文件从一台机器传到另一台机器上。
 * 这是第四问的服务器端, 用于发送文件
 * 参考文献:
 * http://book.51cto.com/art/200809/89224.htm
 *
 */
public class problem4new {

    public static final int PORT = 30000;
    //定义每个数据报的最大大小为4K
    private static final int DATA_LEN = 40960;
    //定义该服务器使用的DatagramSocket
    private DatagramSocket socket = null;
    //定义接收网络数据的字节数组
    byte[] inBuff = new byte[DATA_LEN];
    //以指定字节数组创建准备接受数据的DatagramPacket对象
    private DatagramPacket inPacket =
            new DatagramPacket(inBuff , inBuff.length);
    //定义一个用于发送的DatagramPacket对象
    private DatagramPacket outPacket;
    //定义一个字符串，表示文件路径，服务器发送该文件
    private static String fileName1 = "C:\\Users\\ZNing\\Desktop\\111.txt";

    public static byte[] getBytesFromFile(File f) throws Exception{
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[50000];
            for (int n;(n = stream.read(b)) != -1;) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e){
        }
        return null;
    }

    public void init()throws IOException
    {
        try
        {
            //创建DatagramSocket对象
            socket = new DatagramSocket(PORT);
            //文件对象创建
            File file1 = new File(fileName1);
            //采用循环接受数据
            for (int i = 0; i < 50000 ; i++ )
            {
                //读取Socket中的数据，读到的数据放在inPacket所封装的字节数组里。
                socket.receive(inPacket);
                //判断inPacket.getData()和inBuff是否是同一个数组
                System.out.println(inBuff == inPacket.getData());
                //将接收到的内容转成字符串后输出
                System.out.println(new String(inBuff ,
                        0 , inPacket.getLength()));
                //从字符串数组中取出一个元素作为发送的数据
                byte[] sendData = new byte[0];
                try {
                    sendData = getBytesFromFile(file1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //以指定字节数组作为发送数据、以刚接受到的DatagramPacket的
                //源SocketAddress作为目标SocketAddress创建DatagramPacket。
                outPacket = new DatagramPacket(sendData ,
                        sendData.length , inPacket.getSocketAddress());
                //发送数据
                socket.send(outPacket);
            }
        }
        //使用finally块保证关闭资源
        finally
        {
            if (socket != null)
            {
                socket.close();
            }
        }
    }

    public static void main(String[] args) throws Exception{
    System.out.println("服务器已就绪");
        new problem4new().init();
    }
}
