//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//This is the filter for the command grep
package cs131.pa1.filter.sequential;

public class SubFilter_grep extends SequentialFilter{
	private String key = null; //keyword of the command grep
	//constructor gets the key word from builder
	public SubFilter_grep(String key){
		this.key = key;
	}
	
	@Override
	//process each line to check if it contains the keyword
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		if(line.contains(key)){
			return line;
		}
		return null;
	}

}
