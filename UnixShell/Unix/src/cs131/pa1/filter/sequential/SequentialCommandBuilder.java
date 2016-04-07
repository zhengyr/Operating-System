//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//This is the command builder that manages each command and filter them into filters
package cs131.pa1.filter.sequential;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import cs131.pa1.filter.Message;

public class SequentialCommandBuilder {
	private static LinkedList<SequentialFilter> validCmds = new LinkedList<SequentialFilter>(); // linkedlist that store the valid command
	private static boolean check = true; // check if the input statement is true
	// constructor that checks if the command is valid and then process the command
	public SequentialCommandBuilder(String command) {
		check = checkIfValid(command);
		if (check) {
			linkFilters(validCmds);
			processCommand();
		}
	}

	// method that process each valid command
	private static void processCommand() {
		SequentialFilter currentFilter = null;
		// while loop process every command before the last command
		while (validCmds.size() > 1) {
			currentFilter = validCmds.remove();
			currentFilter.process();
		}

		// the last command
		currentFilter = validCmds.remove();
		currentFilter.output = new LinkedList<String>();

		// if the last command has output, print them out in the console
		if (currentFilter != null) {
			currentFilter.process();
			if (currentFilter.output != null && !currentFilter.output.isEmpty()) {
				int size = currentFilter.output.size();
				for (int i = 0; i < size; i++) {
					System.out.println(currentFilter.output.remove());
				}
			}
		}
	}

	// method checks if the command is valid, could check if the command is valid when initializing the command and check it in the constructor, 
	//but there are some cases that are similar so it might be easier to check it before initializing them. This also saves some spaces for store invalid command
	private static boolean checkIfValid(String command) {
		String[] cmds = { "pwd", "ls", "cd", "cat", "grep", "wc", "uniq", ">" }; // array that sorts all the command
		command = command.replaceAll(">", "\\|>");
		String[] subCmds = command.split("\\|");
		String prevCmd = null;

		// for loop that go through each
		for (int i = 0; i < subCmds.length; i++) {
			String subCmd = subCmds[i];
			Queue<String> commands = new LinkedList<String>();
			Scanner read = new Scanner(subCmd);
			// split each command with its output
			while (read.hasNext()) {
				String currentCmd = read.next().trim();
				commands.add(currentCmd);
			}
			read.close();

			// start error handling, if command is valid, create the filter for it
			SequentialFilter currentFilter = null;
			String cmd = commands.poll();
			// errors that they have in common
			if (!Arrays.asList(cmds).contains(cmd)) {
				System.out.println(Message.COMMAND_NOT_FOUND.with_parameter(subCmd));
				
			} else if ((cmd.equals("grep") || cmd.equals(">"))  &&  (commands.isEmpty() || prevCmd == null)) { //there is no message about no argument, so I use the message of no input
				System.out.println(Message.REQUIRES_INPUT.with_parameter(subCmd));
				
			} else if ((cmd.equals("grep") || cmd.equals(">") || cmd.equals("wc") || cmd.equals("uniq"))  &&  (prevCmd.equals("cd") || prevCmd.equals(">"))) {
				System.out.println(Message.REQUIRES_INPUT.with_parameter(subCmd));

			}else if( (cmd.equals("wc")||cmd.equals(">"))  &&  (validCmds.isEmpty())){
				System.out.println(Message.REQUIRES_INPUT.with_parameter(subCmd));
				
			}else{//check specific error for each cmd and then creates filter for valid command
				currentFilter = createFilters(cmd, commands, subCmd);
			}
			//if there is any command that is invalid, immediately change check and stop processing
			if (currentFilter != null) {
				prevCmd = cmd;
				validCmds.add(currentFilter);
			} else {
				check = false;
				return check;
			}
		}
		return true;
	}
	
	//this method creates each filter if the command is valid
	private static SequentialFilter createFilters(String cmd, Queue<String> commands, String subCmd){
		switch(cmd){
			case "pwd":  
				return new SubFilter_pwd();
			case "ls": 
				return new SubFilter_ls();
			case "cd":
				return checkCd(commands, subCmd);
			case "cat": 
				return checkCat(commands, subCmd);
			case "grep": 
				return new SubFilter_grep(commands.poll());
			case "wc":
				return new SubFilter_wc();
			case "uniq":
				return new SubFilter_uniq();
			case ">":
				return new SubFilter_redic(commands.poll());
			} 
		return null;
	}
	
	//method that checks if cat command is valid 
	private static SequentialFilter checkCat(Queue<String> commands, String subCmd) {
		if (commands.isEmpty()) {
			System.out.println(Message.REQUIRES_INPUT.with_parameter(subCmd));
			return null;
		}
		String[] names = new String[commands.size()];
		int j = 0;
		// needs to check if the files exist
		while (!commands.isEmpty()) {
			String filename = commands.poll();
			File f = new File(filename);
			if (!f.exists()) {
				System.out.println(Message.FILE_NOT_FOUND.with_parameter(subCmd));
				commands.clear();
				return null;
			}
			names[j] = filename;
			j++;
		}
		return new SubFilter_cat(names);
	}

	//method that checks if cd command is valid
	private static SequentialFilter checkCd(Queue<String> commands, String subCmd) {
		if (commands.isEmpty()) {
			System.out.println(Message.REQUIRES_INPUT.with_parameter(subCmd));
		} else {
			// cd needs to first check if the directory exist
			String dir = commands.poll();
			if (dir.equals("..") || dir.equals(".")) {
				return new SubFilter_cd(dir);
			} else if (Files.isDirectory(Paths.get(
				SequentialREPL.currentWorkingDirectory + System.getProperty("file.separator") + dir))) {
				return new SubFilter_cd(dir);
			} else {
				System.out.println(Message.DIRECTORY_NOT_FOUND.with_parameter(subCmd));
			}
		}
		return null;
	}

	// this method link filters together
	private static void linkFilters(List<SequentialFilter> filters) {
		Iterator<SequentialFilter> itr = filters.iterator();
		if (itr.hasNext()) {
			SequentialFilter prevFilter = itr.next();
			while (itr.hasNext()) {
				SequentialFilter currentFilter = itr.next();
				if (currentFilter != null) {
					currentFilter.setPrevFilter(prevFilter);
				}
				prevFilter.setNextFilter(currentFilter);
				prevFilter = currentFilter;
			}
		}
	}
}
