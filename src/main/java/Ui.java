import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public Ui() {

    }

    /**
     * prints out a partition line
     */
    public void line() {
        System.out.println("_____________________________________");
    }

    /**
     * prints out the greet message
     */
    public void greet_msg() {
        line();
        System.out.println("Hello! i'm Duke\n" + "What can i do for you?\n");
        line();
    }

    /**
     * prints out the bye message
     */
    public void bye_msg() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    /**
     * reads user command
     * @return user command
     */
    public String read_command() {
        return sc.nextLine();
    }

    /**
     * prints default message if user enters an invalid or unknown message
     */
    public void default_msg() {
        line();
        System.out.println("☹ OOPS!!! please enter a valid command\n" +
                "done\t" + "list\n" + "delete\t" + "find\n" + "todo\t"
                + "deadline\n" + "event\t");
        line();
    }
}
