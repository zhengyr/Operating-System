//Yiran Zheng
//zhengyr@brandeis.edu
//This is class for basic client that would enter the server and take the whole server
package edu.Brandeis.cs131.Common.YourName;

import edu.Brandeis.cs131.Common.Abstract.Industry;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;

public class BasicClient extends MyClient {
	//constructor that would call the super constructor
	public BasicClient(String label, Industry industry) {
		super(label, industry);
		// TODO Auto-generated constructor stub
	}
	//override the to string so it would return "basic" for basic client
	public String toString() {
        return String.format("%s BASIC %s", this.getIndustry(), this.getName());
    }
	
}
