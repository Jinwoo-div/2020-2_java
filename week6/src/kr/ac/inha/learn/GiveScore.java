package kr.ac.inha.learn;

import java.util.ArrayList;
import java.util.Scanner;

public class GiveScore {
	public static void main(String[] args) {
	ArrayList<ArrayList<String>> student = new ArrayList<>();
	ArrayList<String> data;
	int i = 0;
	while(true) {
		i++;
		data = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		System.out.println("학생" + i + "의 이름을 입력하세요? : ");
		String name = in.nextLine();
		if (name.equals("exit"))
			break;
		System.out.println("점수" + i + "을 입력하세요 : ");
		int score = in.nextInt();
		data.add(name);
		data.add(caculateScore(score));
		student.add(data);
		}
	int size = student.size();
	for (int j = 0; j < size; j++) 
		System.out.println((j+1) + "번 학생 " + student.get(j).get(0) + "의 등급은 " + student.get(j).get(1) + "입니다.");
	}
	
	public static String caculateScore(int score) {
		score = score/10;
		if (score > 8)
			return "A";
		if (score > 7)
			return "B";
		if (score > 6)
			return "C";
		if (score > 5)
			return "D";
		if (score > 4)
			return "E";
		else
			return "F";
	}
}	
