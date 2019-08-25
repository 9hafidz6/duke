import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String h_line = "_____________________________________\n\n";
        String greet_msg = "Hello! i'm Duke\n"
                + "What can i do for you?\n";
        String bye_msg = "Bye. Hope to see you again soon!";
        String msg;

        ArrayList<Task> task_list = new ArrayList<Task> (100);

        System.out.println(h_line + greet_msg + h_line);

        while(true) {
            msg = sc.nextLine();
            if (msg.equals("bye")) {
                System.out.println(h_line + "\t" + bye_msg + "\n"+ h_line);
                break;
            }
            else if (msg.equals("list")) { //if user enters the command "list", output all entries in the list
                System.out.println(h_line);
                for(int a = 0; a < task_list.size(); a++) { //outputs all entry of list from 1 ~ 100 if have
                    System.out.println((a+1) + ".[" + task_list.get(a).getStatusIcon() + "] "+ task_list.get(a).description + "\n");
                }
                System.out.println(h_line);
            }
            else if (msg.indexOf("done") == 0) {
                String[] token = msg.split(" ");

                task_list.get(Integer.parseInt(token[1]) - 1).markDone();

                System.out.println(h_line + "\tNice! I've marked this task as done:\n"
                        + "\t[" + task_list.get(Integer.parseInt(token[1]) - 1).getStatusIcon()
                        + "] " + task_list.get(Integer.parseInt(token[1]) - 1).description + "\n"
                        + h_line);
            }
            else { //if user enters a command, add into task_list
                boolean flag = false; //flag to check whether task exists in list
                for(int a = 0; a < task_list.size(); a++) {
                    if (task_list.get(a).description.equals(msg)) { //if item has already been added, don't add
                        flag = true;
                        break;
                    }
                }
                if (flag) { //if task already exist, output msg has already been added
                    System.out.println(h_line + "\t" + msg + " has already been added" + "\n" + h_line);
                }
                else { //if task does not exist in list, add to list
                    System.out.println(h_line + "\t" + "added: " + msg + "\n" + h_line); //output the added "msg"
                    Task t = new Task(msg);
                    task_list.add(t);
                }
            }
        }
    }
}
