package pc;

import java.util.List;

/**
 * 消费者
 * @author Kuo
 *采用的是signal()和wait()方法设计
 */
public class Consumer implements Runnable{
	private List<PCData> queue;
	private int sum=0;
	public Consumer(List<PCData> queue){
		this.queue=queue;
	}
	@Override
	public void run() {
		try{
			while(true){
				if(Thread.currentThread().isInterrupted())
					//判断当前线程是否被中断，如果中断则break
					break;
				PCData data=null;
				//声明缓冲区类型
				
				synchronized (queue) {
					//把wait和notifyAll放到synchronized同步锁中，如果使用必须先获得锁
					if(queue.size()==0){
			        //如果缓冲队列大小为空，则消费者必须等待
					queue.wait();
					//当前线程等待
					queue.notifyAll();
					//notifyAll唤醒在此对象监视器的所有进程
				}
				
				data=queue.remove(0);
				sum+=data.getData();
				System.out.println("消费者:"+Thread.currentThread().getId()+"\t"+"消费:"+data.getData()+"\t"+"TotalNum:"+sum);
				
				}
				
				Thread.sleep(1000);
				//等待1000毫秒
			}	
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}

}
