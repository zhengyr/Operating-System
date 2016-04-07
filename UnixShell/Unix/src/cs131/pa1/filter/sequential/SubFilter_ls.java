//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//This is the filter for the command ls
package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.LinkedList;

public class SubFilter_ls extends SequentialFilter{
	
	//process that find all the files in the current directory and then add it to output
	public void process(){
		File f = new File(SequentialREPL.currentWorkingDirectory);
		String [] lts = f.list();
		for(String name: lts){
			output.add(name);
		}
	}
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
