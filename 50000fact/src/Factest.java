import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class Factest {
	public static void main(String[] args) throws IOException {
		BigInteger b = new BigInteger("1");
		for (int i = 0; i<50000 ; i++) {
			b = b.multiply(new BigInteger("" + (i+1)));
		}
		BufferedOutputStream bs = null;
		try{
			bs = new BufferedOutputStream(new FileOutputStream("C:/homeWork/test.txt"));
			bs.write(b.toString().getBytes());
	    }	catch (Exception e) {
		e.getStackTrace();
		}
		finally {
			bs.close();
		}
		
	}
}
