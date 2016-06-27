package exp67;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by ZNing on 16/6/27.
 *
 * 6. 利用多线程、Socket编程 、GUI编写一个聊天室，客户端可以发送信息 ，
 * 服务器将接收到的每个用户端 信息实时发送给其他客户端。（提示 ：服务器端
 * 为每个客户启动一 个单独线程接受服务器发送来的其他信息 ，客户端启动一个单
 * 独线程接受服务器发送来的其他信息)
 *
 * 此为客户端程序
 */



public class ChatRoomClient {

	  public static void main(String[] args) {
	
			new ClientUI();
			

	}
}

class ClientUI extends JFrame
{
	   private static final int DEFAULT_WIDTH = 400;
	   private static final int DEFAULT_HEIGHT =300;
	   private static JTextArea textarea;
	   private static JTextField textfield;
	   private ClientThread CT;
	   private PipedWriter GUItoSender;
	   private PipedReader ReceivertoGUI;
	   public ClientUI() {
		setLocation(400, 200);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		textarea=new JTextArea(40,40);
		JScrollPane Jsp=new JScrollPane(textarea);
		add(Jsp,BorderLayout.NORTH);
		
		textfield =new JTextField();
		textfield.addActionListener(new TFListener());
		add(textfield,BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter()
        {
           public void windowClosing(WindowEvent event)
           {
             try {
            	 if(CT.getSocket()!=null)
				 CT.getSocket().close();
            	 CT.Stop();
            	  
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
           }
       });

        pack();
        
        
		try {
			CT=new ClientThread(new Socket("localhost", 5000));
			GUItoSender=new PipedWriter();
			ReceivertoGUI =new PipedReader();
			CT.getSenderPipedReader().connect(GUItoSender);
			CT.getReceiverPipedWriter()
			.connect(ReceivertoGUI);
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		setVisible(true);
		start();
	}
	   public PipedWriter getPipedWriter()
	   {
		   return GUItoSender;
	   }
	   public static String getString()
	   {
		   return textfield.getText();
	   }
       public void start()
       {   BufferedReader br=new BufferedReader(ReceivertoGUI);
    	   while(true)
    	   {
    		   try {
				String str=br.readLine();
				if(str==null)
					break;
				textarea.append(str+"\r\n");
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
					try {
						if(br!=null)br.close();
					} catch (IOException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
			}
    	   }
       }
		private  class TFListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String str = textfield.getText().trim();
				textarea.append(str+"\r\n");		
				str=str+"\r\n";
				textfield.setText("");	
			        try {
			        	GUItoSender.write(str.toCharArray());
			        	GUItoSender.flush();
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
			}
			
		}
		class ClientThread 
		{
			Thread receiver;
			Thread sender;
			PipedReader pr;
			PipedWriter pw;
			Socket s;
		    public ClientThread(Socket s) {
				// TODO �Զ����ɵĹ��캯�����
		    	this.s=s;
		    	Receiver re=new Receiver(s);
		    	Sender se=new Sender(s);
		        pr=se.getPipedReader();
		        pw=re.getPipedWriter();
		    	receiver=new Thread(re);
		    	sender=new Thread(se);
		    	receiver.start();
		    	sender.start();
			}
		    public void Stop()
		    {
		    	
		        receiver.stop();
		        
		        sender.stop();
		        
		    }
		    public Socket getSocket()
		    {return s;}
		    public PipedReader getSenderPipedReader()
		    {
		    	return pr;
		    }
		    public PipedWriter getReceiverPipedWriter()
		    {
		    	return pw;
		    }
		    class Sender implements  Runnable {
		    	private Socket s;
		    	private PrintWriter out=null;
		    	private BufferedReader reader=null;
		    	private PipedReader pr;
		    	public Sender(Socket s) {
		    		// TODO �Զ����ɵĹ��캯�����
		    		this.s=s;
		    		pr=new PipedReader();
		    	}
		    	
		    	public PipedReader getPipedReader()
		    	{
		    		return pr;
		    	}
		    	public PrintWriter getOut()
		    	{
		    		return out;
		    	}
		    	public void run() {
		    		try {
		    			//������ʲô ��Ȼ�ò�����
		    			//OutputStreamWriter osw=new OutputStreamWriter(s.getOutputStream());
		    			//BufferedWriter bw=new BufferedWriter(osw);
		    			//out=new PrintWriter(bw);
		    			out = new PrintWriter(s.getOutputStream(),true);
	                	reader=new BufferedReader(pr);
		                while (s.isConnected()) {
				    		 try {
				    			 out.println(reader.readLine());
				             } catch (IOException e) {
				                 e.printStackTrace();
				             }
		                }
		    		} catch (IOException e) {
		    			// TODO �Զ����ɵ� catch ��
		    			e.printStackTrace();
		    		}finally
		    		{
		    			try {
		    		         if(out!=null)out.close();
		    		         if(reader!=null)reader.close();
		    		         if(pr!=null)pr.close();
		    			} catch (IOException e) {
		    				// TODO �Զ����ɵ� catch ��
		    				e.printStackTrace();
		    			}
		             
		    		}
		    	}
		    }
		    class Receiver implements  Runnable {

		        private Socket s;
		        BufferedReader in;
		        private PipedWriter pw;
		        public PipedWriter getPipedWriter()
		        {
		        	return pw;
		        }
		        public Receiver(Socket s) {
		        	this.s=s;
		        	pw=new PipedWriter();
		    	}
		    	public void run() {
		    		// TODO �Զ����ɵķ������
		    		try {
		    			InputStreamReader isr=new InputStreamReader(s.getInputStream());
		    			in =new BufferedReader(isr);
		    			while(true)
		    		    {
		    			    String str=in.readLine();
		    			    if(str==null)break;
		    			    str+="\r\n";
		    			    pw.write(str.toCharArray());
				        	pw.flush();
		    			    //System.out.println(str);
		    			}
		    		} catch (IOException e) {
		    			// TODO �Զ����ɵ� catch ��
		    			e.printStackTrace();
		    		}finally {
		    				try {
		    					if(in!=null)in.close();
		    				} catch (IOException e) {
		    					// TODO �Զ����ɵ� catch ��
		    					
		    				}
		    		}
		    	}
		    	
		    }
		}

}


