package exp67;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZNing on 16/6/27.
 *
 * 6. 利用多线程、Socket编程 、GUI编写一个聊天室，客户端可以发送信息 ，
 * 服务器将接收到的每个用户端 信息实时发送给其他客户端。（提示 ：服务器端
 * 为每个客户启动一 个单独线程接受服务器发送来的其他信息 ，客户端启动一个单
 * 独线程接受服务器发送来的其他信息
 *
 * 此为服务器端程序
 */

public class ChatRoomServer {
	public static void main(String[] args) {
		ChatRoomServer server = new ChatRoomServer();
    	server.start();
	}
	List<Socket> clientSockets = new ArrayList<Socket>();
	public void start() {
        try {
        	ServerSocket server = new ServerSocket(5000);
            while (true) {
                Socket s = server.accept( );
                System.out.println("�ͻ�������");
                clientSockets.add(s);
                Thread t = new Thread(new Server(s));
                t.start();
            }
        }
        catch (Exception e) { 
            e.printStackTrace( );
        } 
    }
	
	
	
	class Server implements  Runnable {
		Socket s;
		BufferedReader in;
		PrintWriter out;
		Server(Socket s) {
			this.s = s;
		}
		public void run() {
			boolean flag = true;
			try {
			InputStreamReader isr =new InputStreamReader(s.getInputStream());
			in =new BufferedReader(isr);
			
                 while(flag)
                 {
                	
						
						String str;
						str = in.readLine();
						if(str==null){
		                	clientSockets.remove(s);
		                	//�ɷ������˹ر�socket���ͻ����ٹرգ����ᱨ���������connection reset����
		                	break;
		                }
						System.out.println(str);
						for (Socket ss : clientSockets) {
		                	if (ss!=s) 
		                	{   
		                		out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(ss.getOutputStream())));
		                	    out.println("�û�"+clientSockets.indexOf(s)+" : "+str);
		                	    out.flush();
		                	}
		                }
				}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("�ͻ��˹ر�");
		}finally {
					try {
						if (in!=null ) in.close();
						if (out!=null ) out.close( );
						s.close( );
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	           }    
	           
	    }        
   }
		
}
	



