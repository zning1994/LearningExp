package exp5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

/**
 * Created by ZNing on 2016-5-25.
 * 4. 编写程序，利用数据包（Datagram）将一个文件从一台机器传到另一台机器上。
 * 这是第四问的服务器端, 用于开设组播并发送文件
 * 参考文献:
 * 1. http://www.cnblogs.com/hq-antoine/archive/2012/02/11/2347250.html
 * 2. 本程序利用的组播原理解释: http://book.51cto.com/art/200809/89225.htm
 * http://book.51cto.com/art/200809/89224.htm
 *
 */

class QuoteServerThread extends Thread
{
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    protected static int MulitiAddrPort = 7788;//在这里修改组播端口
    protected static String multiAddr = "localhost";//在这里修改组播地址

    public QuoteServerThread() throws IOException {
        this("QuoteServerThread");
    }

    public QuoteServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(7786);//数据流socket端口,注意保护不要修改

        try {
            in = new BufferedReader(new FileReader("hello.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }
    }

    public void run() {
        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];

                // 获取请求
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // 进行响应
                String dString = null;
                if (in == null)
                    dString = new Date().toString();
                else
                    dString = getNextQuote();
                buf = dString.getBytes();

                // 向用户发送响应
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            }
            catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }

    protected String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }
}

class MulticastServerThread extends QuoteServerThread
{
    private long FIVE_SECONDS = 50000;

    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }

    public void run() {
        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];

                // 构造引用
                String dString = null;
                if (in == null)
                    dString = new Date().toString();
                else
                    dString = getNextQuote();
                buf = dString.getBytes();

                // 发送
                //这里getByName需要填写服务器所在IP地址(创建组播)
                InetAddress group = InetAddress.getByName(multiAddr);
                DatagramPacket packet =new DatagramPacket(buf,buf.length,group, MulitiAddrPort);
                socket.send(packet);

                // 休眠
                try {
                    sleep((long)(Math.random() * FIVE_SECONDS));
                }
                catch (InterruptedException e) { }
            }
            catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }
}

public class problem4 {
    public static void main(String[] args) throws Exception{
        new MulticastServerThread().start();
    }
}
