package week2;
import java.util.Scanner;

public class Hello {
	public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int score = 45;
	char grade = ' ';
	if (score >= 90) {
		grade = 'A';
	}
	else if (score >= 80) {
		grade = 'B';
	}
	else if (score >= 70) {
		grade = 'C';
	}
	else {
		grade = 'D';
	}
}
}

