import java.util.*;
import java.io.*;

public class Driver {
	private static String[] supported = {"and", "or", "add", "addi", "sll", "sub", "slt", "beq", "bne", "lw", "sw", "j", "jr", "jal"};

	public static void main(String[] argv) throws FileNotFoundException {
		ArrayList<Label> labelList = new ArrayList<Label>();
		System.out.println(argv.length);
		File file= new File(argv[0]);
		Scanner lineScanner = new Scanner(file);
		int lineNumber= 0;
		while (lineScanner.hasNext()) {
			Scanner currentLine = new Scanner(lineScanner.nextLine());
			String firstWord = currentLine.next();
			if (firstWord.contains(":")) {
				labelList.add(makeLabel(firstWord, lineNumber));
			}
			lineNumber++;
		}
		lineScanner = new Scanner(file);
		lineNumber = 0;
		while (lineScanner.hasNext()) {
			Scanner currentLine = new Scanner(lineScanner.nextLine());
			String currentWord = lineScanner.next();
			if (!isLabel(currentWord, labelList)) {
				if (isValid(currentWord)) {
					
				}
				else {
					System.exit(-1);
				}
			}
		}
	}

	private static boolean isValid(String instruction) {
		for (String word: supported) {
			if (word.equals(instruction)) {
				return true;
			}
		}
		System.out.println("invalid instruction: "+instruction);
		return false;
	}

	private static class Label {
		public String name;
		public int lineNumber;

		public Label(String name, int lineNumber) {
			this.name=name;
			this.lineNumber=lineNumber;
		}
	}

	private static Label makeLabel(String name, int lineNumber) {
		return new Label(name, lineNumber);
	}

	private static boolean isLabel(String word, ArrayList<Label> labList) {
		for (Label label: labList) {
			if ((label.name).equals(word)) {
				return true;
			}
		}
		return false;
	}
}