//Yiran Zheng
//zhengyr@brandeis.edu
//This is the class for shared client
package edu.Brandeis.cs131.Common.YourName;

import edu.Brandeis.cs131.Common.Abstract.Industry;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;

public class SharedClient extends MyClient {
//constructor for shared client
	public SharedClient(String label, Industry industry) {
		super(label, industry);
		// TODO Auto-generated constructor stub
	}
	//this overrides to string method in super class and will return string "shared"
	public String toString() {
        return String.format("%s SHARED %s", this.getIndustry(), this.getName());
    }
	
}
