import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String h_line = "_____________________________________\n\n";
        String greet_msg = "Hello! i'm Duke\n"
                        + "What can i do for you?\n";
        String bye_msg = "Bye. Hope to see you again soon!";

        String msg = "";

        System.out.println(h_line + greet_msg + h_line);

        while(true) {
            msg = sc.nextLine();
            if(msg.equals("bye")) {
                System.out.println(h_line + "\t" + bye_msg + "\n"+ h_line);
                break;
            }
            else {
                System.out.println(h_line + "\t" + msg + "\n" + h_line);

            }
        }
    }
}


