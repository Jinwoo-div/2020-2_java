package week6;

import javax.swing.JOptionPane;

public class Middle implements Grade {
	private String math;
	private String score;
	private String science;
	
	public String getMath() {
		return math;
	}

	public void setMath(String math) {
		this.math = math;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScience() {
		return science;
	}

	public void setScience(String science) {
		this.science = science;
	}

	@Override
	public void setGrade() {
		math = JOptionPane.showInputDialog("���������� �Է��ϼ���");
		score = JOptionPane.showInputDialog("���������� �Է��ϼ���");
		science = JOptionPane.showInputDialog("���������� �Է��ϼ���");
		// TODO Auto-generated method stub

	}

	@Override
	public void getGrade() {
		System.out.println("");
		// TODO Auto-generated method stub

	}

}
