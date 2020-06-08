import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class TestWords {
	private static Random rand = new Random();
	
	public static String createTest() {
		String text = "";
		try {
			File myFile = new File("shorterWordList.txt");
			int lines = getNumberOfLines(myFile);
			System.out.println(lines);
			System.out.println("testing");
			ArrayList<Integer> indexesUsed = new ArrayList<Integer>() ;
			for (int x = 0; x < 250; x++) {
				Scanner scan = new Scanner(myFile);
				int index = rand.nextInt(lines);
				while (indexesUsed.contains(index))
					index = rand.nextInt(lines);
				indexesUsed.add(index);
				for (int i = 0; i < index; i++)
					scan.nextLine();
				text += scan.nextLine() + " ";
				scan.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error has occurred.");
			e.printStackTrace();
		}
		return text;
	}
	
	private static int getNumberOfLines(File f) {
		int lineCount = 0;
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				lineCount++;
				s.nextLine();
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error has occurred.");
			e.printStackTrace();
		}
		return lineCount;
	}
}
