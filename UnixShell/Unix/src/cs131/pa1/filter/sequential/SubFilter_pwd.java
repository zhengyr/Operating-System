//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//This is the filter for command pwd 
package cs131.pa1.filter.sequential;

import java.util.LinkedList;

public class SubFilter_pwd extends SequentialFilter{
	//process method that add the result of path to output
	public void process(){
		output = new LinkedList<String>();
		output.add(SequentialREPL.currentWorkingDirectory);
		
	}
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
