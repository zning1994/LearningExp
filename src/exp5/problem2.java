package exp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ZNing on 2016-5-25.
 * 2. 使用 Socket 编写一个服务器端程序，监听 8888 端口，如果接收到客户端发来的 hello 请求，回应一个 hello，其他请求不响应。
 * 这是Problem2的服务器端程序
 */

class SendingSer extends Thread{

    private Socket socket;

    public SendingSer(Socket s) {
        this.socket = s;
    }

    public void sendRun(){
        try {
            //准备服务器端PrintWriter
            PrintWriter writerSerRun = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                //发送给客户端数据
                writerSerRun.println("hello");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        //创建输入流
       //PrintWriter writerSer = null;
        try {
            //准备服务器端PrintWriter
            PrintWriter writerSer = new PrintWriter(socket.getOutputStream(), true);

            //准备命令提示符的输入
            InputStreamReader conReader = new InputStreamReader(System.in);
            BufferedReader bufConReader = new BufferedReader(conReader);
            while(true){
                //发送给客户端数据
                writerSer.println(bufConReader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceivingSer extends Thread{

    private  Socket socket;
    private String str = "hello";

    public ReceivingSer(Socket s) {
        this.socket = s;
    }

    public void run() {
        //准备接收客户端的内容
        //InputStreamReader readerSer  = null;
        try {
            //准备客户端socket输入流

            InputStreamReader readerSer = new InputStreamReader(socket.getInputStream());
            BufferedReader bufReader = new BufferedReader(readerSer);
            while(true){
                //读取客户端数据
                String strrece = bufReader.readLine();
                System.out.println("Server, receive : " + strrece);
                if(str.equals(strrece)){
                    PrintWriter writerSerHello = new PrintWriter(socket.getOutputStream(), true);
                    writerSerHello.println("hello");
                }

            }

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.print("向客户端的连接异常，已中断");
        }

    }
}


public class problem2 {
    public static void main(String args[]){

        System.out.println("服务器端已启动");

            try {
                //创建服务器端socket
                ServerSocket svrSocket = new ServerSocket(8888);

                //与客户端建立连接
                Socket socket = svrSocket.accept();

                //while(true){
                    //读取客户端数据
                    SendingSer sendingSer = new SendingSer(socket);
                    sendingSer.start();

                    //发送给客户端数据
                    ReceivingSer receivingSer = new ReceivingSer(socket);
                    receivingSer.start();
                //}

            } catch (IOException e) {
                //e.printStackTrace();
                System.out.print("创建服务器失败，请检查端口占用状态，服务已中断");
            }
    }

}
