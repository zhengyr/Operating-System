//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//This is the filter for command >
package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import cs131.pa1.filter.Filter;

public class SubFilter_redic extends SequentialFilter {
	File f;
	String name;
	//constructor set up the directory
	public SubFilter_redic(String filename) {
		name = SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR
				+ filename;
	}
	//put the input into file
	public void process() {
		f = new File(name);
		if (f.exists()) {
			f.delete();
		}
		try {
			PrintWriter pw = new PrintWriter(name);
			while (!input.isEmpty()) {
				String line = input.poll();
				if (line != null) {
					pw.println(line);
				}
			}
			if (this.isDone())
				pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
