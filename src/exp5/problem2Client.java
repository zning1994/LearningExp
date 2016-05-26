package exp5;

import java.io.*;
import java.net.Socket;

/**
 * Created by ZNing on 2016-5-25.
 * 2. 使用 Socket 编写一个服务器端程序，监听 8888 端口，如果接收到客户端发来的 hello 请求，回应一个 hello，其他请求不响应。
 * 这是Problem2的测试客户端程序
 */

class Sending extends Thread{

    private  Socket socket;

    public Sending(Socket s) {
        this.socket = s;
    }

    public void run() {
        //创建输入流
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            //准备输入流
            InputStreamReader conReader = new InputStreamReader(System.in);
            BufferedReader bufConReader = new BufferedReader(conReader);

            while(true){
                //给服务器发数据
                writer.println(bufConReader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Receiving extends Thread{

    private  Socket socket;

    public Receiving(Socket s) {
        this.socket = s;
    }

    public void run() {
        //准备接收服务器的内容
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufReader = new BufferedReader(reader);

            while(true){
                //服务器发送的数据接收回来
                System.out.println("Client, receive : " + bufReader.readLine());
            }

        } catch (IOException e) {
           // e.printStackTrace();
            System.err.print("向服务器的连接异常，已中断");
        }

    }
}

public class problem2Client {
    public static void main(String args[]){

        System.out.println("客户端已启动");

        try {
            //创建Socket连接服务器
            Socket socket = new Socket("127.0.0.1", 8888);

            //while(true){
                //给服务器发数据
                Sending sending = new Sending(socket);
                sending.start();

                //服务器发送的数据接收回来
                Receiving receiving = new Receiving(socket);
                receiving.start();
            //}

        } catch (Exception e) {
            //e.printStackTrace();
            System.err.print("无法找到服务器，连接异常，已中断");
        }
    }
}
