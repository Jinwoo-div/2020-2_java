package week7;

public class Source1 {
	public static void main(String[] args) {
		int[] array={0, 1, 2};
		try{
			System.out.println(array[3]);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(e.toString());
			System.out.println("정상 처리되었습니다.");
		}
	}
}
