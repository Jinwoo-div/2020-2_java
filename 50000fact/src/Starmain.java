
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Starmain {
	public static void main(String[] args) throws IOException {
		Factorial12 a = new Factorial12(50000);
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

class Factorial12 {
	private int key;
	private String sum;
	private ArrayList<Integer> num = new ArrayList<Integer>();
	private ArrayList<Integer> starSum = new ArrayList<Integer>();
	private ArrayList<Integer> allNum = new ArrayList<Integer>();
	private ArrayList <int[]> aList = new ArrayList<int[]>();
	
	public Factorial12(int key) {
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
			int arr[] = new int[tmpNum.length()];
			for (int j = 0 ; j < tmpNum.length(); j++) {
				arr[j] = tmpNum.charAt(j) - '0';
			}
			aList.add(arr);
		}
	}

	public String getCaculate() {
		int count = 1;
		int zeroCount = 0;
		for (int i = 0; i < aList.get(0).length ; i++) {
			num.add(aList.get(0)[i]);
		}
		int s = allNum.size();
		for (int i = 0; i < s; i++) {
			while(true) {
				int last = num.size() - 1;
				if (num.lastIndexOf(0) != last) {
					break;
				}
				zeroCount++;
				num.remove(last);
			}
			starSum.clear();
			String tmpNum = allNum.get(i+1).toString();
			for (int j = 0; j < tmpNum.length(); j++) {
				starSum.add(tmpNum.charAt(j) - '0');
			}
			ArrayList<Integer> tnum = new ArrayList<Integer>(starString(num, starSum));
			num.clear();
			for (int j = tnum.size() - 1; j > -1; j--) {
				num.add(0, tnum.get(j));
			}
			System.out.println(count++);
		}
		String result = "";
		for (int i = 0; i < num.size(); i++) {
			result += num.get(i).toString();
		}
		return result;
	}
	ArrayList<Integer> starString(ArrayList<Integer> int1, ArrayList<Integer> int2) {
		ArrayList<Integer> tmpint = new ArrayList<Integer>();
		ArrayList<Integer> tmpint2 = new ArrayList<Integer>();
		int len1 = int1.size();
		int len2 = int2.size();
		int carry = 0;
		int coun = 0;
		int tmpnu = int2.get(len2 - 1);
		for (int i = len1 - 1; i > -1; i--) {
			int tmpSum = (int1.get(i) * tmpnu) + carry;
			if (tmpSum > 9) {
				carry = tmpSum / 10;
				tmpSum -= carry*10;
			}
			else {
				carry = 0;
			}
			tmpint2.add(0, tmpSum);
			for (int j = coun; j > 0; j--) {
				tmpint2.add(0);
			}
			coun++;
			if (carry != 0 && i == 0) {
				tmpint2.add(0, carry);
				carry = 0;
			}
		}
		for (int k = len2 - 2; k > -1; k--) {
			int tmpnum = int2.get(k);
			for (int i = len1 - 1; i > -1; i--) {
				int tmpSum = (int1.get(i) * tmpnum) + carry;
				if (tmpSum > 9) {
					carry = tmpSum / 10;
					tmpSum -= carry*10;
				}
				else {
					carry = 0;
				}
				tmpint.add(0, tmpSum);
				for (int j = coun; j > 0; j--) {
					tmpint.add(0);
				}
				coun++;
				if (carry != 0 && i == 0) {
					tmpint.add(0, carry);
					carry = 0;
				}	
			}
			ArrayList<Integer> tmpint3 = new ArrayList<Integer>(plusArr(tmpint, tmpint2));
			tmpint2.clear();
			for (int i = tmpint3.size() - 1; i > -1; i--) {
				tmpint2.add(0, tmpint3.get(i));
			}
			tmpint.clear();
		}
		return tmpint2;
	}
	
	ArrayList<Integer> plusArr(ArrayList<Integer> int1, ArrayList<Integer> int2) {
		int carry = 0;
		while(int2.size() < int1.size()) {
			int2.add(0, 0);
		}
		while(int1.size() < int2.size()) {
			int1.add(0, 0);
		}
		int len = int1.size();
		for (int i = len - 1 ; i > -1; i--) {
			int tmpsum = int1.get(i);
			if (int2.get(i) != 0) {
				tmpsum += int2.get(i);
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
			int1.set(i, tmpsum);
			if (carry == 1 && i == 0) {
				int1.add(0, 1);
				carry = 0;
			}
		}
		return int1;
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
		for (int i = 0; i < len; i++) {
			if (int1.charAt(0) == '0') {
				if (int1.equals("0")) break;
				int1 = int1.substring(1);
			}
			else {
				break;
			}
		}
		return int1;
	}
}
