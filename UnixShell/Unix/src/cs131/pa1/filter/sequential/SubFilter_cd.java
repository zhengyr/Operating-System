//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//This is the filter for change directory
package cs131.pa1.filter.sequential;
import java.util.*;
public class SubFilter_cd extends SequentialFilter{
	String location;
	
	//constructor that set the location we want to go
	public SubFilter_cd(String loc){
		location = loc;
	}
	
	//set up the directory from REPL
	public void process(){
		if(location.equals(".")){
			SequentialREPL.currentWorkingDirectory = SequentialREPL.currentWorkingDirectory;
		}else if(location.equals("..")){
			SequentialREPL.currentWorkingDirectory = SequentialREPL.currentWorkingDirectory.substring(0, SequentialREPL.currentWorkingDirectory.lastIndexOf(FILE_SEPARATOR));
		}else{
			SequentialREPL.currentWorkingDirectory = SequentialREPL.currentWorkingDirectory + FILE_SEPARATOR + location;
		}
		
	}
	@Override
	protected String processLine(String line) {
		return null;
	}

}
