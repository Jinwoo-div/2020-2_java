package week7;

public class Source7 {
	public static void main(String[] args) {
		Integer[] ia = {1, 2, 3, 4, 5};
		Character[] ca = {'H', 'E', 'L', 'L', 'O'};
		
		Utils.showArray(ia);//메서드의 generic 일경우 클래스와 다르게 타입생략가능 파라미터로 들어간 변수의 자료형을 그대로 T로 가져감
		Utils.<Character>showArray(ca);
		
		System.out.println(Utils.getLast(ia));
	}
	static class Utils{
		public static <AA> void showArray(AA[] a) {
			for (AA t: a)//제네릭 타입변수가 굳이 T일 필요는 없다. 근데 T를 쓰는게 국룰이기 때문에 지켜주자
				System.out.printf("%s ", t);
			System.out.println();
		}
		public static <T> T getLast(T[] a) {
			return a[a.length - 1];
		}
	}// 첫번쨰 T랑 두번째 T랑 아무 상관 없음. 그냥 각자 함수에서 놀뿐..
}