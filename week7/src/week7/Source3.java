package week7;

import java.util.Scanner;

public class Source3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			square(in.nextLine());
			square2(in.nextLine());
			square3(in.nextLine());
		}	catch (NumberFormatException e) {
			System.out.println("정수가 아닙니다.");
		}
	}
	private static void square(String s) throws NumberFormatException {
		int n = Integer.parseInt(s);
		System.out.println(n*n);
	}
	private static void square2(String s) throws NumberFormatException {
		int n = Integer.parseInt(s);
		System.out.println(n*n);
	}
	private static void square3(String s) throws NumberFormatException {
		int n = Integer.parseInt(s);
		System.out.println(n*n);
	}
}
