//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//This is the filter that process the command cat
package cs131.pa1.filter.sequential;
import java.io.*;
import java.util.*;
public class SubFilter_cat extends SequentialFilter{
	//array that gets all the names of the files
	private String[] names;
	//constructor that gets the names of files from user
	public SubFilter_cat(String[] names){
		this.names = names;
	}
	//process that scan each files and then output the contents in the files
	public void process(){
		for(String name: names){
			File f = new File(name);
			try {
				Scanner readInputs = new Scanner(f);
				while(readInputs.hasNextLine()){
					output.add(readInputs.nextLine());
				} 
			}catch (FileNotFoundException e){ //catch the file not found exception
				e.printStackTrace();
			}
		}
		
			
	}
	@Override
	protected String processLine(String line){
		// TODO Auto-generated method stub
		return null;
	}

}
