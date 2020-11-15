package week11;

public class Source6 {
	public static void main(String[] args) {
		Counter c1 = new Counter("느긋한");
		Counter c2 = new Counter("급한1");
		Counter c3 = new Counter("급한2");
		Counter c4 = new Counter("급한3");
		Counter c5 = new Counter("급한4");
		Counter c6 = new Counter("급한5");
		c1.setPriority(Thread.MIN_PRIORITY);
		c2.setPriority(2);
		c3.setPriority(3);
		c4.setPriority(4);
		c5.setPriority(5);
		c6.setPriority(Thread.MAX_PRIORITY);
		c6.start();
		c2.start();
		c1.start();
		c3.start();
		c4.start();
		c5.start();
	}
}

class Counter extends Thread {
	private int count = 0;
	
	public Counter(String name) {
		setName(name);
	}
	
	public void run() {
		while (count++ < 5) {
			System.out.print(getName() + "->");
			try {
				sleep(500);
			}catch (InterruptedException e) {
			}
		}
	}
}
