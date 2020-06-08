import java.util.*;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class TypingTest extends JFrame {
	private final int FRAME_HEIGHT = 800, FRAME_WIDTH = 800;
	private JTextArea testWords = new JTextArea(TestWords.createTest());
	
	public TypingTest() {
		JPanel panel = new JPanel();
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("TypingTest");
		this.add(panel);
		
		panel.add(testWords);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		System.out.println(TestWords.createTest());
		new TypingTest();
	}
}
