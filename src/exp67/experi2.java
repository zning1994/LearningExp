package exp67;
/**
 * Created by ZNing on 16/6/27.
 * 2.系统随机生成 10000个数 ，构造5个线程并利用它们的 Join方法计算这些随机数的最大值。
 */

public class experi2
{
	public static void main(String[] args) throws Exception
	{
		int max=0;
		Thread2[] ta=new Thread2[5];
		for(int i=0;i<5;i++)
		{
			ta[i]=new Thread2("Thread "+i);
			ta[i].start();
			
		}
		for(int i=0;i<5;i++)
		{
			ta[i].join();
			if(max<ta[i].getMax())
				max=ta[i].getMax();
		}
		System.out.println("The max in 10000 numbers is: "+max);
		
		
	}
}

class Thread2 extends Thread
{
	int max;
	public Thread2(String name)
	{
	   super(name);
	}
	public int getMax()
	{
		return max;
	}
	
     	public void run()
     	{
     	   int i;
     	   int random ;
     	   for(max=0, i=0;i<10000;i++)
     	   {
     		   random=(int)(1+Math.random()*(5600-1+1));
     		   System.out.print(currentThread().getName());
     		   System.out.print(" RandomNumber now is : "+random);
     		   System.out.println(" max now is : "+max);
     		   if(max<random)
     		   max=random;  
     	   }
     	}
}