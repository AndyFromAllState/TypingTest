import java.util.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TypingTest extends JFrame {
	private final int FRAME_HEIGHT = 800, FRAME_WIDTH = 1350;
	private JTextPane testPanel = new JTextPane();
	
	public TypingTest() {
		JPanel panel = new JPanel();
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("TypingTest");
		this.add(panel);
		
		Font font = new Font("Comic Sans MS", Font.BOLD, 35);
		
		testPanel.setEditable(false);
		testPanel.setFont(font);
		testPanel.setText(TestWords.createTest());
		
		//centers the text
		StyledDocument doc = testPanel.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);  
		
		//puts the testPanel into a scrollable pane
		JScrollPane scrollable = new JScrollPane (testPanel);
		scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollable.setPreferredSize(new Dimension(FRAME_WIDTH - 20, FRAME_HEIGHT/2));
		
		panel.add(scrollable);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TypingTest();
	}
}
