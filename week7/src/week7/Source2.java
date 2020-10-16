package week7;

public class Source2 {
	public static void main(String[] args) {	
	int dividend = 10;
	try {
		int divisor = Integer.parseInt("123");
		throw new Exception();// 이 코드를 지나가면 반드시 에러 발생 + 앞에서 뭐했는지 상관없음 그냥 예외 만들어줌
//		System.out.println(dividend/divisor); // if 문같이 앞에서 발생한 예외처리와 동일한 게 하나 더 있을때 통일 시키기위해 사용
	}	catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("원소가 존재하지 않습니다.");
	}	catch (NumberFormatException e) {
		System.out.println("숫자가 아닙니다.");
	}	catch (ArithmeticException e) {
		System.out.println("0으로 나눌 수 없습니다.");
	}	catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.toString());
		System.out.println("기타예외"); 
	}	finally {
		System.out.println("항상 실행됩니다.");
	}
	System.out.println("종료");
	}
}
