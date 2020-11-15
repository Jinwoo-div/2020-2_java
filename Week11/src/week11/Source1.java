package week11;
import java.awt.GraphicsEnvironment;

public class Source1 {
	public static void main(String[] args) {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String [] fontName = e.getAvailableFontFamilyNames();
		System.out.println(fontName[0]);
	}
}
