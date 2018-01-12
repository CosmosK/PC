package pc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
public static void main(String[] args){
	List<PCData> queue=new ArrayList<PCData>();
	int MAX_SIZE=5;
	int[] a={10,9,8,7,6,5,4,3,2,1};
	Producer p=new Producer(queue,MAX_SIZE,a);
	//实例化生产者
	Consumer c1=new Consumer(queue);
	Consumer c2=new Consumer(queue);
	ExecutorService service=Executors.newCachedThreadPool();
	service.execute(p);
	service.execute(c1);
	service.execute(c2);
}
}
