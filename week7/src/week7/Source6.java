package week7;
class printList<T> {
	T arr[];
	printList(T arr[]) {
		this.arr = arr;
//		arr = new T(); Ŭ���� ������ ������ �� �ö󰡴µ� �̶�  T�� ���� ���� �����Ƿ� ����
	}
	void printArr() {
		for (T a:arr) {
			System.out.println(a);
		}
	}
}

public class Source6 {
	public static void main(String[] args) {
		Integer ia[] = new Integer[5];
		ia[0] = 1;
		ia[1] = 2;
		ia[2] = 3;
		ia[3] = 4;
		ia[4] = 5;
		printList<Integer> p = new printList(ia);
	}
}

//��ü ������ Ÿ���� �����ϰ� �� Ÿ���� �迭 ��ü�� �޾� ������ִ� Ŭ������ ���׸����� �����غ��ÿ�