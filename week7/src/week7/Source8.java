package week7;

public class Source8 {
	public static void main(String[] args) {
		Integer[] ia = {1, 2, 3, 4, 5};//integer Ŭ������ int�� �ٸ��� null ���� �� ����.
		Double[] da = {1.0, 2.0, 3.0, 4.0, 5.0};//Double Ŭ������ ��������
		Character[] ca = {'H', 'E', 'L', 'L', 'O'};//���׸������� �⺻�ڷ��� ���� x Ŭ������������ �����
		Utils.showArray(ia);
		Utils.showArray(da);
	}
	
	static class Utils{//extends ���� �ڿ� ������ Ÿ�����θ� ���׸� ���� Number �ؿ��� integer, double�� �����ֵ�����
		public static < T extends Number> void showArray(T[] a) {
			for (T t: a)
				System.out.printf("%", t);
			System.out.println();
		}
	}
}
