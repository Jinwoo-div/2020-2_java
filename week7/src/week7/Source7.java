package week7;

public class Source7 {
	public static void main(String[] args) {
		Integer[] ia = {1, 2, 3, 4, 5};
		Character[] ca = {'H', 'E', 'L', 'L', 'O'};
		
		Utils.showArray(ia);//�޼����� generic �ϰ�� Ŭ������ �ٸ��� Ÿ�Ի������� �Ķ���ͷ� �� ������ �ڷ����� �״�� T�� ������
		Utils.<Character>showArray(ca);
		
		System.out.println(Utils.getLast(ia));
	}
	static class Utils{
		public static <AA> void showArray(AA[] a) {
			for (AA t: a)//���׸� Ÿ�Ժ����� ���� T�� �ʿ�� ����. �ٵ� T�� ���°� �����̱� ������ ��������
				System.out.printf("%s ", t);
			System.out.println();
		}
		public static <T> T getLast(T[] a) {
			return a[a.length - 1];
		}
	}// ù���� T�� �ι�° T�� �ƹ� ��� ����. �׳� ���� �Լ����� ���..
}