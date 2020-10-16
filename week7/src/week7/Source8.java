package week7;

public class Source8 {
	public static void main(String[] args) {
		Integer[] ia = {1, 2, 3, 4, 5};//integer 클래스는 int와 다르게 null 가질 수 있음.
		Double[] da = {1.0, 2.0, 3.0, 4.0, 5.0};//Double 클래스도 마찬가지
		Character[] ca = {'H', 'E', 'L', 'L', 'O'};//제네릭에서도 기본자료형 지원 x 클래스형식으로 줘야함
		Utils.showArray(ia);
		Utils.showArray(da);
	}
	
	static class Utils{//extends 사용시 뒤에 나오는 타입으로만 제네릭 제한 Number 밑에는 integer, double과 같은애들있음
		public static < T extends Number> void showArray(T[] a) {
			for (T t: a)
				System.out.printf("%", t);
			System.out.println();
		}
	}
}
