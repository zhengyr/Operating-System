//Yiran Zheng
//zhengyr@brandeis.edu
//This is the class for basic server that extend the server class
package edu.Brandeis.cs131.Common.YourName;

import java.util.ArrayList;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Industry;
import edu.Brandeis.cs131.Common.Abstract.Server;

public class BasicServer extends Server {
	private int status; //0 means no client, 1 means 1 basic client, 2 means fully occupied
	//array that keep track of how many shared client in the industry and what their industry are
	private ArrayList<Industry> currentIndustries = new ArrayList<>();
	public BasicServer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	//this is the method for connectInner that will be called from connect in the super class
	//this method is synchronized because if many client try to access the the field status and the array
	//they might mess up these fields in server
	@Override
	public synchronized boolean connectInner(Client client) {
		// TODO Auto-generated method stub
		//if the client is basic client
		if(client instanceof BasicClient){
			if(status == 0){
				status = 2;
				return true;
			}
			//if the client is shared client
		}else if(client instanceof SharedClient){
			SharedClient sclient = (SharedClient) client;
			if(status == 0){
				status = 1;
				currentIndustries.add(sclient.getIndustry());
				return true;
			}else if (status == 1 && currentIndustries!=null && !currentIndustries.isEmpty()){
				if(currentIndustries.get(0) != sclient.getIndustry()){
					status = 2;
					currentIndustries.add(sclient.getIndustry());
					return true;
				}
			}
		}
		return false;
	}
	//this method will be called from disconnect from the super class
	//this method is synchronized because if two thread tries to change the array or status, they might mess up the result
	@Override
	public synchronized void disconnectInner(Client client) {
		// TODO Auto-generated method stub
		if(client instanceof BasicClient){//remove basic client
			status = 0;
		}else if(client instanceof SharedClient && currentIndustries!=null && !currentIndustries.isEmpty()){//remove shared client
			SharedClient sclient = (SharedClient) client;
			if(status == 1){//only one share client
				status = 0;
				currentIndustries.remove(0);
			}else if(status == 2){//two shared client, remove one of them
				int i = currentIndustries.indexOf(sclient.getIndustry());
				currentIndustries.remove(i);
				status = 1;
			}
		}
	}
}
