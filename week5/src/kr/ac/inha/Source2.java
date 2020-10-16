package kr.ac.inha;

class Person{
	
}
class Student extends Person{
	
}

public class Source2 {
//	public static void main(String[] args) {
//		Student s = new Student();
//		Person p = new Person();
//		
//		System.out.println(p instanceof Person);
//		
//		System.out.println(s instanceof Student);
//		
//		System.out.println(s instanceof Student);
//		
//		downcast(s);
//	}

	static void downcast(Person p) {
		if (p instanceof Student) {
			Student s = (Student) p;
			System.out.println("ok. 하향 타입 변환");
		}
	}
}