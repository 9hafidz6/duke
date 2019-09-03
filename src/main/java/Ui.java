import java.util.Scanner;

public class Ui {

    Scanner sc = new Scanner(System.in);

    public Ui() {

    }
    public void line() {
        System.out.println("_____________________________________");
    }

    public void greet_msg() {
        line();
        System.out.println("Hello! i'm Duke\n" + "What can i do for you?\n");
        line();
    }

    public void bye_msg() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public String read_command() {
        return sc.nextLine();
    }
}
