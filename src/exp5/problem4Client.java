package exp5;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by ZNing on 2016-5-26.
 * 4. 编写程序，利用数据包（Datagram）将一个文件从一台机器传到另一台机器上。
 * 这是第四问的客户端，用于接收数据文件
 *
 */
public class problem4Client {

    private static String receMulitiAddr = "localhost";//在这里修改组播地址
    private static int receMulitiAddrPort = 7788;//在这里修改组播端口

    public static void main(String[] args) throws Exception{
        MulticastSocket socket = new MulticastSocket(receMulitiAddrPort);
        //这里getByName需要填写服务器所在IP地址
        InetAddress address = InetAddress.getByName(receMulitiAddr);
        //加入组播
        socket.joinGroup(address);
        DatagramPacket packet;

        for (int i = 0; i < 5; i++)
            {
            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(packet.getData());
            System.out.println("Quote of the Moment: " + received);
            }
        //离开组播
        socket.leaveGroup(address);
        socket.close();
    }
}
