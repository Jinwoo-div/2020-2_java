
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Starmain {
	private static ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> arr2 = new ArrayList<ArrayList<Integer>>();
	private static boolean sig = false;
	public static void main(String[] args) throws IOException {
		Runnable[] name = new Factorial12[50];
		Thread[] name2 = new Thread[50];
		
		for (int i = 0; i < 50; i++) {
			name[i] = new Factorial12((1000*i)+1, 1000*(i+1));
			name2[i] = new Thread(name[i]);
			name2[i].start();
		}
		while (true) {
			if (arr.size() == 50) {
				Runnable[] name3 = new starTwo[50];
				Thread[] name4 = new Thread[50];
				sig = true;
				for (int i = 0; i < 25; i++) {
					name3[i] = new starTwo(arr.get(i*2), arr.get((i*2)+1));
					name4[i] = new Thread(name3[i]);
					name4[i].start();//sig true arr2 add
				}
				while (true) {
					if (arr2.size() == 25) {
						arr.clear();
						break;
						}
				}
				sig = false;
				for (int i = 0; i < 12; i++) {
					name3[i] = new starTwo(arr2.get(i*2), arr2.get((i*2)+1));
					name4[i] = new Thread(name3[i]);
					name4[i].start();
				}
				while (true) {
					if (arr.size() == 12) {
						arr.add(arr2.get(24));
						arr2.clear();
						break;
					}
				}
				sig = true;
				for (int i = 0; i < 6; i++) {
					name3[i] = new starTwo(arr.get(i*2), arr.get((i*2)+1));
					name4[i] = new Thread(name3[i]);
					name4[i].start();
				}
				while (true) {
					if (arr2.size() == 6) {
						arr2.add(arr.get(13));
						arr.clear();
						break;
					}
				}
				sig = false;
				for (int i = 0; i < 3; i++) {
					name3[i] = new starTwo(arr2.get(i*2), arr2.get((i*2)+1));
					name4[i] = new Thread(name3[i]);
					name4[i].start();
				}
				while (true) {
					if (arr.size() == 3) {
						arr.add(arr.get(7));
						arr2.clear();
						break;
					}
				}
				sig = true;
				for (int i = 0; i < 2; i++) {
					name3[i] = new starTwo(arr.get(i*2), arr.get((i*2)+1));
					name4[i] = new Thread(name3[i]);
					name4[i].start();
				}
				while (true) {
					if (arr2.size() == 2) {
						arr.clear();
						break;
					}
				}
				sig = false;
				for (int i = 0; i < 1; i++) {
					name3[i] = new starTwo(arr2.get(i*2), arr2.get((i*2)+1));
					name4[i] = new Thread(name3[i]);
					name4[i].start();
				}
				while (true) {
					if (arr.size() == 1) {
						arr2.clear();
						break;
					}
				}
				String result = "";
				for (int i = 0; i < arr.size(); i++) {
					result += arr.get(i).toString();
				}
				exportFile(result);
				break;
//					starSum.clear();
//					String tmpNum = arr.get(i).toString();
//					for (int j = 0; j < tmpNum.length(); j++) {
//						starSum.add(tmpNum.charAt(j) - '0');
//					}
//					ArrayList<Integer> tnum = new ArrayList<Integer>(starString(num, starSum));
//					num.clear();
//					for (int j = tnum.size() - 1; j > -1; j--) {
//						num.add(0, tnum.get(j));
//					}
//					System.out.println(i++);
//				}
			}
		}
	}
	static void call(ArrayList<Integer> result) {
		if (sig == true) {
			arr2.add(result);
		}
		else {
			arr.add(result);
		}
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
class starTwo implements Runnable {
	private ArrayList<Integer> num = new ArrayList<Integer>();
	private ArrayList<Integer> starSum = new ArrayList<Integer>();
	
	public starTwo(ArrayList<Integer> arr1 ,ArrayList<Integer> arr2) {
		num = arr1;
		starSum = arr2;
	}
	public void run() {
		Starmain.call(starString(num, starSum));
	}
	public ArrayList<Integer> starString(ArrayList<Integer> int1, ArrayList<Integer> int2) {
		ArrayList<Integer> tmpint = new ArrayList<Integer>();
		ArrayList<Integer> tmpint2 = new ArrayList<Integer>();
		int len1 = int1.size();
		int len2 = int2.size();
		int carry = 0;
		int coun = 1;
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
				if (carry != 0 && i == 0) {
					tmpint.add(0, carry);
					carry = 0;
				}	
			}
			for (int j = coun; j > 0; j--) {
				tmpint.add(0);
			}
			coun++;
			System.out.println(k);
			ArrayList<Integer> tmpint3 = new ArrayList<Integer>(plusArr(tmpint, tmpint2));
			tmpint2.clear();
			for (int i = tmpint3.size() - 1; i > -1; i--) {
				tmpint2.add(0, tmpint3.get(i));
			}
			tmpint.clear();
		}
		return tmpint2;
	}
	public ArrayList<Integer> plusArr(ArrayList<Integer> int1, ArrayList<Integer> int2) {
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
}

class Factorial12 implements Runnable {
	private ArrayList<Integer> num = new ArrayList<Integer>();
	private ArrayList<Integer> starSum = new ArrayList<Integer>();
	private ArrayList<Integer> allNum = new ArrayList<Integer>();
	
	public Factorial12(int key1, int key2) {
		for (int i = key1; i < key2 + 1; i++) {
			allNum.add(i);
		}
	}
	public void getResult(ArrayList<Integer> result) {
		Starmain.call(result);
	}
	public void run() {
		getCaculate();
		getResult(num);
	}
	public ArrayList<Integer> getCaculate() {
		int c = allNum.size();
		int zeroCount = 0;
		String firstNum = allNum.get(0).toString();
		for (int j = 0; j < firstNum.length(); j++) {
			num.add(firstNum.charAt(j) - '0');
		}
		int s = allNum.size();
		for (int i = 1; i < s; i++) {
			while(true) {
				int last = num.size() - 1;
				if (num.lastIndexOf(0) != last) {
					break;
				}
				zeroCount++;
				num.remove(last);
			}
			starSum.clear();
			String tmpNum = allNum.get(i).toString();
			for (int j = 0; j < tmpNum.length(); j++) {
				starSum.add(tmpNum.charAt(j) - '0');
			}
			ArrayList<Integer> tnum = new ArrayList<Integer>(starString(num, starSum));
			num.clear();
			for (int j = tnum.size() - 1; j > -1; j--) {
				num.add(0, tnum.get(j));
			}
			System.out.println(c--);
		}
		for (int i = 0; i < zeroCount; i++) {
			num.add(0);
		}
		return num;
	}
	ArrayList<Integer> starString(ArrayList<Integer> int1, ArrayList<Integer> int2) {
		ArrayList<Integer> tmpint = new ArrayList<Integer>();
		ArrayList<Integer> tmpint2 = new ArrayList<Integer>();
		int len1 = int1.size();
		int len2 = int2.size();
		int carry = 0;
		int coun = 1;
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
				if (carry != 0 && i == 0) {
					tmpint.add(0, carry);
					carry = 0;
				}	
			}
			for (int j = coun; j > 0; j--) {
				tmpint.add(0);
			}
			coun++;
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
} 