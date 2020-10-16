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
		math = JOptionPane.showInputDialog("수학점수를 입력하세요");
		score = JOptionPane.showInputDialog("영어점수를 입력하세요");
		science = JOptionPane.showInputDialog("과학점수를 입력하세요");
		// TODO Auto-generated method stub

	}

	@Override
	public void getGrade() {
		System.out.println("");
		// TODO Auto-generated method stub

	}

}
