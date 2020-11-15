package week11;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Source4 {
	public static void main(String[] args) {
		Runnable task = () -> {
			for (int i = 0; i < 5 ; i++) {
				System.out.print("잘가 .");
				try {
					Thread.sleep(500);
				}
				catch(InterruptedException e) {
				}
			}
		};
		Executor exec = Executors.newCachedThreadPool();
		exec.execute(task);
		
		for (int i = 0; i < 5; i++) {
			System.out.print("안녕. ");
			try{
				Thread.sleep(500);
			}
			catch (InterruptedException e) {
			}
		}
		System.exit(0);
	}
}
