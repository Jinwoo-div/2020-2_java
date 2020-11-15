package week11;

public class Source3 {
	public static void main(String[] args) {
		Thread t = new Thread(new MyRunnable());
		t.start();
		for (int i = 0; i < 5; i++) {
			System.out.println("안녕");
			try{
				Thread.sleep(500);
			}
			catch(InterruptedException e) {
				 e.printStackTrace();
			}
		}
	}
}

class MyRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			System.out.println("잘가");
			try{
			Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}