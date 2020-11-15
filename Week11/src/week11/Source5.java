package week11;

public class Source5 {
	public static void main(String[] args) {
		Runnable task = () -> {
			try {
				while (true) {
					System.out.println("실행중 ...");
					Thread.sleep(1);
				}
			}catch (InterruptedException e) {
					System.out.println("종료준비중");
				}
				System.out.println("정상종료");
			};
		Thread t = new Thread(task);
		t.start();
		try {
			Thread.sleep(2);
		}
		catch (InterruptedException e) {
		}
		t.stop();
		t.interrupt();
	}

}
