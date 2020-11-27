
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StrMain {
	public static void main(String[] args) throws IOException {
		Factorialstr a = new Factorialstr(50000);
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

class Factorialstr {
	private int key;
	private int carry;
	private String sum;
	private String num = "";
	private String starSum = "";
	
	public Factorialstr(int key) {
		this.key = key;
		sum = Integer.toString(this.key);
	}

	public String getCaculate() {
		int count = 1;
		num = "1";
		for (int i = 1; i < key; i++) {
			starSum = "";
			starSum = num;
			for (int j = 0 ; j < i; j++) {
				int len = num.length();
				for (int k = len - 1; k > -1; k--) {
					while(starSum.length() < k + 1) {
						starSum = "0" + starSum;
					}
					int tmpsum = (num.charAt(k) - '0');
					if (starSum.charAt(k) != '0') {
						tmpsum += (starSum.charAt(k) - '0');
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
					num = num.substring(0, k) + tmpsum + num.substring(k+1);
					if (carry == 1 && k == 0) {
						num = carry + num;
						carry = 0;
					}
				}
			}
			System.out.println(count++);
		}
		
		return num;
	}
}