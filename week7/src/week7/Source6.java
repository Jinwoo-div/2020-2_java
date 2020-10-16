package week7;
class printList<T> {
	T arr[];
	printList(T arr[]) {
		this.arr = arr;
//		arr = new T(); 클래스 정보는 컴파일 시 올라가는데 이때  T에 대한 정보 없으므로 오류
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

//객체 생성시 타입을 지정하고 이 타입의 배열 객체를 받아 출력해주는 클래스를 제네릭으로 구현해보시오