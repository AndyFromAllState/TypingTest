import java.util.*;
import java.util.Timer;
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
	private String inputtedText = "";
	private JLabel timerLabel = new JLabel("60s");
	private JLabel wpmLabel = new JLabel("0wpm");
	private JLabel accuracyLabel = new JLabel("Accuracy: ");
	private boolean running = false, ended = false;
	private double countdown = 60;
	private double timeElapsed = 0;
	private double missed = 0;
	
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
		 
		//puts the testPanel into a scrollable pane |NOTE - NOT USED DUE TO AUTO SCROLL ERROR -> https://www.java-forums.org/awt-swing/12498-getting-jscrollpane-stop-auto-scrolling.html|
		JScrollPane scrollable = new JScrollPane (testPanel);
		scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollable.setPreferredSize(new Dimension(FRAME_WIDTH - 20, FRAME_HEIGHT/2));
		
		testPanel.setPreferredSize(new Dimension(FRAME_WIDTH - 20, FRAME_HEIGHT/2));
		
		textInputPanel.setFont(font);
		textInputPanel.setPreferredSize(new Dimension(FRAME_WIDTH - 20, FRAME_HEIGHT/2));
		textInputPanel.addKeyListener(KL);
		
		panel.add(wpmLabel);
		panel.add(timerLabel);
		panel.add(accuracyLabel);
		panel.add(testPanel);
		panel.add(textInputPanel);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TypingTest();
	}

	public void startTimer() {
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {
			public void run() {
				if (running) {
					countdown();
				}
				if (countdown <= 0) {
					ended = true;
					running = false;
					timerLabel.setText("Time's Up");
					wpmLabel.setText(String.format("%.1f wpm", calculateWPM()));
					System.out.println("ended");
					timer.cancel();
				}
			}
		};
		timer.schedule(tt, 0, 100);
	}
	
	public void countdown() {
		countdown -= .1;
		timeElapsed += .1;
		timerLabel.setText(String.format("%.1f s", countdown));
		wpmLabel.setText(String.format("%.1f wpm", calculateWPM()));
		accuracyLabel.setText(String.format("Accuracy: %.1f%%", calculateAccuracy()));
	}

	public double calculateWPM() {
		return textInputPanel.getText().length() / 5 / (timeElapsed / 60);
	}
	
	public double calculateAccuracy() {
		return (textInputPanel.getText().length()/(textInputPanel.getText().length()+ missed)) * 100.0;
	}
	
	private class KeyboardListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			if (ended) return;
			if (e.getKeyChar() == testPanel.getText().charAt(0)) {
				if (!running && !ended) {
					running = true;
					startTimer();
				}
				inputtedText += testPanel.getText().substring(0,1);
				textInputPanel.setEditable(true);
				testPanel.setText(testPanel.getText().substring(1));
			}
			else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				textInputPanel.setEditable(false);
			else {
				missed++;
				textInputPanel.setEditable(false);

			}
			

			System.out.println(inputtedText);

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
