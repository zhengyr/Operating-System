//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//The is the filter for wc
package cs131.pa1.filter.sequential;
import java.util.Scanner;
public class SubFilter_wc extends SequentialFilter{
	public int lines = 0;
	public int words = 0;
	public int chars = 0;

	//this method count how many words and characters in the input and adds them together
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		lines++;
		Scanner read = new Scanner(line);
		while(read.hasNext()){
			String word = read.next();
			words++;
			chars = chars + word.length();
		}
		if(isDone()){
			return lines + " " + words + " " + chars;
		}
		return null;
	}

}
