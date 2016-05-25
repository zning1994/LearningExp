package exp5;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by ZNing on 2016-5-25.
 * 3、编写程序，利用数据报（Datagram）查询端口占用情况。
 *
 */
public class problem3 {
    public static void main(String[] args) {
        for (int port=1024;port<=65535;port++){
            DatagramSocket server  = null;
            try {
                server = new DatagramSocket(port);
                server.close();
            } catch (SocketException e) {
                //e.printStackTrace();
                System.out.println("There is a server in port "+port+".");
            }

        }
    }
}
