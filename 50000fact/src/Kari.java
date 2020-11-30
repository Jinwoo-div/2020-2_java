
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Kari {
	public static void main(String[] args) throws IOException {
		Factorial2 a = new Factorial2(50000);
		String result = a.getCaculate();
		exportFile(result);
	}
	static void exportFile(String result) throws IOException{
		BufferedOutputStream bs = null;
		try{
			bs = new BufferedOutputStream(new FileOutputStream("C:/homeWork/result.txt"));
			bs.write(result.getBytes());
	}	catch (Exception e) {
		e.getStackTrace();
		}
		finally {
			bs.close();
		}
	}
}

class Factorial2 {
	private int key;
	private int kariCount = 1;
	private String sum;
	private String num;
	private String starSum;
	private ArrayList<Integer> allNum = new ArrayList<Integer>();
	private ArrayList <String> aList = new ArrayList<String>();
	
	public Factorial2(int key) {
		this.key = key;
		sum = Integer.toString(this.key);
		for (int i = 1; i < key + 1; i++) {
			allNum.add(i);
		}
		int k = key - 1;
		for (int i = 0; i < key/2; i++) {
			allNum.set(i, allNum.get(i) * allNum.get(k));
			allNum.remove(k);
			k--;
		}
		for (int i = 0; i < key/2; i++) {
			String tmpNum = allNum.get(i).toString();
			aList.add(tmpNum);
		}
	}

	public String getCaculate() {
		for (int i = 0; i < key/2 - 1 ; i++) {
			num = aList.get(i);
			starSum = aList.get(i+1);
			int size = num.length() - starSum.length();
			if (size != 0) {
				while(num.length() != starSum.length()) {
					starSum = "0" + starSum;
				}
			}
			if (num.length() % 2 == 1) {
				num = "0" + num;
				starSum = "0" + starSum;
			}
			aList.set(i + 1, karitsu(num, starSum));
		}
		return aList.get(key/2 - 1);
	}
	String karitsu(String int1, String int2) {
		String left, right, ans;
		if (int1.length() % 2 == 1) {
			int1 = "0" + int1;
			int2 = "0" + int2;
		}
		while (int1.length() != int2.length()) {
			if (int1.length() > int2.length()) {
				int2 = "0" + int2;
			}
			else {
				int1 = "0" + int1;
			}
		}
		if (int1.length() > 2 || int2.length() > 2) {
			String int1Right = int1.substring(int1.length()/2);
			String int2Right = int2.substring(int2.length()/2);
			right = karitsu(int1Right, int2Right);
			String int1Left = int1.substring(0, int1.length()/2);
			String int2Left = int2.substring(0, int2.length()/2);
			left = karitsu(int1Left, int2Left);
			ans = karitsu(plusString(int2Left, int2Right), plusString(int1Left, int1Right));
			kariCount++;
		}
		else {
			if(int1.equals("00") || int2.equals("00")) {
				 return "0";
			}
			int[][] num = new int[2][2];
			num[0][0] = int1.charAt(0) -'0';
			num[0][1] = int1.charAt(1) -'0';
			num[1][0] = int2.charAt(0) -'0';
			num[1][1] = int2.charAt(1) -'0';
			int a = num[0][0] * num[1][0];
			int b = num[0][1] * num[1][1];
			int c = (num[0][0] + num[0][1]) * (num[1][0] + num[1][1]);
			int altC = c - a - b;
			String result = Integer.toString((a*100) + (altC* 10));
			return result;
		}
		for (int i = 0; i < kariCount*2; i++) {
			if (left.equals("0")) {
				break;
			}
			left = left + "0";
		}
		for (int i = 0; i < kariCount; i++) {
			if (ans.equals("0")) {
				break;
			}
			ans = ans + "0";
		}
		return plusString(plusString(left, right), ans);
	}
	String starString(String int1, String int2) {
		int result = Integer.parseInt(int1) * Integer.parseInt(int2);
		return Integer.toString(result);
	}
	String plusString(String int1, String int2) {
		int carry = 0;
		while(int2.length() < int1.length()) {
			int2 = "0" + int2;
		}
		while(int1.length() < int2.length()) {
			int1 = "0" + int1;
		}
		int len = int1.length();
		for (int i = len - 1 ; i > -1; i--) {
			int tmpsum = (int1.charAt(i) - '0');
			if (int2.charAt(i) != '0') {
				tmpsum += (int2.charAt(i) - '0');
			}
			if (carry == 1) {
				tmpsum += 1;//carry 있음 추가로 더해줌
			}
			if (tmpsum > 9) {// carry 발생
				
				tmpsum -= 10; // 남길수 만들기
				carry = 1;
			}
			else {
				carry = 0;
			}
			int1 = int1.substring(0, i) + tmpsum + int1.substring(i+1);
			if (carry == 1 && i == 0) {
				int1 = carry + int1;
				carry = 0;
			}
		}
		return int1;
	}
}