package pc;

import java.util.List;

/**
 * 生产者
 * @author Kuo
 *
 */
public class Producer implements Runnable{
	private List<PCData> queue;
	private int MAX_SIZE;//缓存区大小
	private int[] a;//生产者的队列
	private int i=0;
	public Producer(List<PCData> queue,int MAX_SIZE,int[] a){
		this.queue=queue;
		this.MAX_SIZE=MAX_SIZE;
		this.a=a;
	}
	@Override
	public void run() {
		try{
			while(true){
				if(Thread.currentThread().isInterrupted())
					break;
				
				PCData data=new PCData();
				synchronized(queue){
				if(queue.size()>=MAX_SIZE){
					//如果缓冲区大于最大值，唤醒其他线程
					//当前线程等待
					queue.notifyAll();
					queue.wait();
				}else{
					data.setData(a[i]);
					i+=1;
					queue.add(data);
					System.out.println("生产者:"+data.getData());
					}
				
				Thread.sleep(500);
				
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
