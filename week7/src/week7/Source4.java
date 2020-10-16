package week7;

public class Source4 {
	public static void main(String[] args) throws Exception{//main에서 처리 안하고 넘길 시 처리 안됨 -> 실행은 됨 정상동작X
		Thread.sleep(100);
		System.out.println("예외처리가 반드시 필요한 코드");
	}
}
