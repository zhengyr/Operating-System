package cs131.pa1.filter;

public enum Message {
    WELCOME("Welcome to the Unix-ish command line.\n"),
    NEWCOMMAND("> "),
    GOODBYE("Thank you for using the Unix-ish command line. Goodbye!\n"),
    FILE_NOT_FOUND("At least one of the files in the command [%s] was not found."),
    DIRECTORY_NOT_FOUND("The directory specified by the command [%s] was not found."),
    COMMAND_NOT_FOUND("The command [%s] was not recognized."),
    REQUIRES_INPUT("The command [%s] requires input.")
    ;
    private final String message;
    
    private Message(String message){
        this.message=message;
    }
    public String toString(){
        return this.message;
    }
    public String with_parameter(String parameter){
        if(this==WELCOME || this==NEWCOMMAND || this == GOODBYE){
            return this.toString();
        }
        return String.format(this.message, parameter.trim());
    }
    
}
