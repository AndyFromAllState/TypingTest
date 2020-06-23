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
	private JTextPane textInputPanel = new JTextPane();
	
	public TypingTest() {
		KeyboardListener KL = new KeyboardListener();
		
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
		
		testPanel.setPreferredSize(new Dimension(FRAME_WIDTH - 20, FRAME_HEIGHT/2));
		
		textInputPanel.setFont(font);
		textInputPanel.setPreferredSize(new Dimension(FRAME_WIDTH - 20, FRAME_HEIGHT/2));
		textInputPanel.addKeyListener(KL);
		
		panel.add(testPanel);
		panel.add(textInputPanel);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TypingTest();
	}
	
	private class KeyboardListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == testPanel.getText().charAt(0)) {
				textInputPanel.setEditable(true);
				testPanel.setText(testPanel.getText().substring(1));
				System.out.println("hi");
			}
			else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				textInputPanel.setEditable(false);
			else
				textInputPanel.setEditable(false);
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
