//Yiran Zheng
//zheng@brandeis.edu
//CS131
//This program includes the read eval print loop that interact with the user 
package cs131.pa1.filter.sequential;
import java.util.*;

import cs131.pa1.filter.Message;

public class SequentialREPL {
	//string that keep tracks of the current directory
	public static String currentWorkingDirectory = null;
	//main program that interact with the user
	public static void main(String[] args){
		//set up the start of the loop
		System.out.print(Message.WELCOME.toString());
		System.out.print(Message.NEWCOMMAND.toString());
		Scanner input = new Scanner(System.in);
		String cmd = input.nextLine();
		currentWorkingDirectory = System.getProperty("user.dir");	
		
		//while loop that start looping, when the user enter exit, exit the unix-ish loop
		while(!cmd.equals("exit")){
			SequentialCommandBuilder runCommand = new SequentialCommandBuilder(cmd);
			System.out.print(Message.NEWCOMMAND.toString());
			cmd = input.nextLine();
		}
		System.out.print(Message.GOODBYE.toString());
	}

	

}
