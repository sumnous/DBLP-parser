import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Vector;

public class Main {

	public static BufferedReader reader;
	public static PrintStream printer;

	public static void main(String args[]) throws Exception {
		reader = new BufferedReader(new FileReader("data/citation.txt"));
		String line = "";
		line = reader.readLine();

		Vector<Paper> papers = new Vector<Paper>();
		Paper paper = new Paper();
		while ((line = reader.readLine()) != null) {
			if (line.length() == 0) {
				papers.add(paper);
				paper = new Paper();
				continue;
			}
			update(paper, line);
		}

		printer = new PrintStream("data/authors.txt");
		for (Paper p : papers) {
			for (String author : p.authors) {
				printer.println(p.indexID + "!@#@!" + author);
			}
		}

		printer = new PrintStream("data/cites.txt");
		for (Paper p : papers) {
			for (String cite : p.citations) {
				printer.println(p.indexID + "!@#@!" + cite);
			}
		}
		printer = new PrintStream("data/papers.txt");
		for (Paper p : papers) {
			printer.println(p.indexID + "!@#@!" + p.year + "!@#@!" + p.title + "!@#@!" + p.conf + "!@#@!" + p.info);
		}
	}

	public static void update(Paper paper, String line) {
		if (line.substring(0, 2).equals("#*")) {
			line = line.substring(2);
			paper.title = line;
			return;
		}
		if (line.substring(0, 2).equals("#@")) {
			line = line.substring(2);
			paper.authors.addAll(Arrays.asList(line.split(",")));
			return;
		}
		if (line.substring(0, 2).equals("#t")) {
			line = line.substring(2);
			paper.year = line;
			return;
		}
		if (line.substring(0, 2).equals("#c")) {
			line = line.substring(2);
			paper.conf = line;
			return;
		}
		if (line.substring(0, 2).equals("#i")) {
			line = line.substring(1);
			paper.indexID = line;
			return;
		}
		if (line.substring(0, 2).equals("#%")) {
			line = line.substring(2);
			paper.citations.add(line);
			return;
		}
		if (line.substring(0, 2).equals("#!")) {
			line = line.substring(2);
			paper.info = line;
			return;
		}
	}
}
