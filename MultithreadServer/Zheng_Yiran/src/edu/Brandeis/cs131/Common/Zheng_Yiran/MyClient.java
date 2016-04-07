//Yiran Zheng
//zhengyr@brandeis.edu
//This is the abstract for client which will extended by basic client and shared client
package edu.Brandeis.cs131.Common.YourName;

import java.util.Random;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Industry;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;

public abstract class MyClient extends Client {
//generate random speed and set the request level to be 3
	public MyClient(String label, Industry industry){
		//super(label, industry, 9, 3);
		super(label, industry, new Random().nextInt(10), 3);
	}
    
}
