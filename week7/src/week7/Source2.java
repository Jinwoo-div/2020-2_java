package week7;

public class Source2 {
	public static void main(String[] args) {	
	int dividend = 10;
	try {
		int divisor = Integer.parseInt("123");
		throw new Exception();// �� �ڵ带 �������� �ݵ�� ���� �߻� + �տ��� ���ߴ��� ������� �׳� ���� �������
//		System.out.println(dividend/divisor); // if ������ �տ��� �߻��� ����ó���� ������ �� �ϳ� �� ������ ���� ��Ű������ ���
	}	catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("���Ұ� �������� �ʽ��ϴ�.");
	}	catch (NumberFormatException e) {
		System.out.println("���ڰ� �ƴմϴ�.");
	}	catch (ArithmeticException e) {
		System.out.println("0���� ���� �� �����ϴ�.");
	}	catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.toString());
		System.out.println("��Ÿ����"); 
	}	finally {
		System.out.println("�׻� ����˴ϴ�.");
	}
	System.out.println("����");
	}
}
