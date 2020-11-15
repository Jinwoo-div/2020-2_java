package week10;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorListener;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.ImageIcon;

public class Note extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Note frame = new Note();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Note() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 552);
		
		ActionListener clic = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand() + " 버튼이 눌렸습니다.");
			}
		};
		
			
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Mfile = new JMenu("파일(F)");
		menuBar.add(Mfile);
		
		JMenuItem newFile = new JMenuItem("새로 만들기(N)");
		Mfile.add(newFile);
		newFile.addActionListener(clic);
		
		JMenuItem newWindow = new JMenuItem("새 창(W)");
		Mfile.add(newWindow);
		newWindow.addActionListener(clic);
		
		JMenuItem open = new JMenuItem("열기(O)...");
		Mfile.add(open);
		open.addActionListener(clic);
		
		JMenuItem save = new JMenuItem("저장(S)");
		Mfile.add(save);
		save.addActionListener(clic);
		
		JSeparator fileSeperator1 = new JSeparator();
		Mfile.add(fileSeperator1);
		
		JMenuItem anotherSave = new JMenuItem("다른 이름으로 저장(A)...");
		Mfile.add(anotherSave);
		anotherSave.addActionListener(clic);
		
		JMenuItem setPage = new JMenuItem("페이지 설정(U)...");
		Mfile.add(setPage);
		setPage.addActionListener(clic);
		
		JMenuItem print = new JMenuItem("인쇄(P)...");
		Mfile.add(print);
		print.addActionListener(clic);
		
		JSeparator fileSeperator2 = new JSeparator();
		Mfile.add(fileSeperator2);
		
		JMenuItem exit = new JMenuItem("끝내기(X)");
		Mfile.add(exit);
		exit.addActionListener(clic);
		
		JMenu Medit = new JMenu("편집(E)");
		menuBar.add(Medit);
		
		JMenuItem undo = new JMenuItem("실행 취소(U)");
		Medit.add(undo);
		undo.addActionListener(clic);
		
		JSeparator editSeperator1 = new JSeparator();
		Medit.add(editSeperator1);
		
		JMenuItem cut = new JMenuItem("잘라내기(T)");
		Medit.add(cut);
		cut.addActionListener(clic);
		
		JMenuItem copy = new JMenuItem("복사(C)");
		Medit.add(copy);
		copy.addActionListener(clic);
		
		JMenuItem paste = new JMenuItem("붙여넣기(P)");
		Medit.add(paste);
		paste.addActionListener(clic);
		
		JMenuItem delete = new JMenuItem("삭제(L)");
		Medit.add(delete);
		delete.addActionListener(clic);
		
		JSeparator editSeperator2 = new JSeparator();
		Medit.add(editSeperator2);
		
		JMenuItem searchBing = new JMenuItem("Bing으로 검색(S)...");
		Medit.add(searchBing);
		searchBing.addActionListener(clic);
		
		JMenuItem find = new JMenuItem("찾기(F)...");
		Medit.add(find);
		find.addActionListener(clic);
		
		JMenuItem findNext = new JMenuItem("다음 찾기(N)");
		Medit.add(findNext);
		findNext.addActionListener(clic);
		
		JMenuItem findPrev = new JMenuItem("이전 찾기(V)");
		Medit.add(findPrev);
		findPrev.addActionListener(clic);
		
		JMenuItem change = new JMenuItem("바꾸기(R)...");
		Medit.add(change);
		change.addActionListener(clic);
		
		JMenuItem move = new JMenuItem("이동(G)...");
		Medit.add(move);
		move.addActionListener(clic);
		
		JSeparator editSeperator3 = new JSeparator();
		Medit.add(editSeperator3);
		
		JMenuItem selectAll = new JMenuItem("모두 선택(A)");
		Medit.add(selectAll);
		selectAll.addActionListener(clic);
		
		JMenuItem time = new JMenuItem("시간/날짜(D)");
		Medit.add(time);
		time.addActionListener(clic);
		
		JMenu Mform = new JMenu("서식(O)");
		menuBar.add(Mform);
		
		JMenuItem autoLine = new JMenuItem("자동 줄 바꿈(W)");
		Mform.add(autoLine);
		autoLine.addActionListener(clic);
		
		JMenuItem font = new JMenuItem("글꼴(F)...");
		Mform.add(font);
		font.addActionListener(clic);
		
		JMenu appearence = new JMenu("보기(V)");
		menuBar.add(appearence);
		
		JMenu zoomInOut = new JMenu("확대하기/축소하기");
		appearence.add(zoomInOut);
		
		JMenuItem zoomIn = new JMenuItem("확대(I)");
		zoomInOut.add(zoomIn);
		zoomIn.addActionListener(clic);
		
		JMenuItem zoomOut = new JMenuItem("축소(O)");
		zoomInOut.add(zoomOut);
		zoomOut.addActionListener(clic);
		
		JMenuItem restoreZoom = new JMenuItem("확대하기/축소하기 기본값 복원");
		zoomInOut.add(restoreZoom);
		restoreZoom.addActionListener(clic);
		
		JCheckBoxMenuItem statusBar = new JCheckBoxMenuItem("상태표시줄(S)");
		appearence.add(statusBar);
		statusBar.addActionListener(clic);
		
		JMenu help = new JMenu("도움말(H)");
		menuBar.add(help);
		
		JMenuItem seeHelp = new JMenuItem("도움말 보기(H)");
		help.add(seeHelp);
		seeHelp.addActionListener(clic);
		
		JMenuItem feedback = new JMenuItem("피드백 보내기(F)");
		help.add(feedback);
		feedback.addActionListener(clic);
		
		JSeparator helpSeparator = new JSeparator();
		help.add(helpSeparator);
		
		JMenuItem info = new JMenuItem("메모장 정보(A)");
		help.add(info);
		info.addActionListener(clic);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\download\\Assignment_#13_201921625_KimDaeYeon.png"));
		contentPane.add(lblNewLabel);
		
		JEditorPane editorPane = new JEditorPane();
		contentPane.add(editorPane);
	}

	private char[] type(Object source) {
		// TODO Auto-generated method stub
		return null;
	}

}
