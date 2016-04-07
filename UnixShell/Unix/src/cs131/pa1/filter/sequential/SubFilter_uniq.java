//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//This program is the filter for command uniq
package cs131.pa1.filter.sequential;

import java.util.HashSet;
import java.util.LinkedList;

public class SubFilter_uniq extends SequentialFilter{
	//hash set that checks duplicates of the lines
	HashSet<String> checkDuplicates = new HashSet<String>();
	@Override
	//processline method that checks if we have seen the line before
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		if(!checkDuplicates.contains(line)){
			checkDuplicates.add(line);
			return line;
		}
		return null;
	}

}
