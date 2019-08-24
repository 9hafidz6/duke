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

        ArrayList<String> task_list = new ArrayList<String> (100);

        System.out.println(h_line + greet_msg + h_line);

        while(true) {
            msg = sc.nextLine();
            if(msg.equals("bye")) { //if user command "bye", end the program
                System.out.println(h_line + "\t" + bye_msg + "\n"+ h_line);
                break;
            }
            else if(msg.equals("list")) { //if user enters the command "list", output all entries in the list
                int a = 0;
                System.out.println(h_line);
                Iterator<String> itr = task_list.iterator();
                while(itr.hasNext()) { //outputs all entry of list from 1 ~ 100 if have
                    a++;
                    System.out.println(a + ". " + itr.next() + "\n");
                }
                System.out.println(h_line);
            }
            else { //if user enters a command, add into task_list
                boolean flag = false; //flag to check whether task exists in list
                Iterator<String> itr1 = task_list.iterator();
                while(itr1.hasNext()) {
                    if(itr1.next().equals(msg)) { //if item has already been added, don't add
                        flag = true;
                        break;
                    }
                }
                if(flag) { //if task already exist, output msg has already been added
                    System.out.println(h_line + "\t" + msg + " has already been added" + "\n"+ h_line);
                }
                else { //if task does not exist in list, add to list
                    task_list.add(msg);
                    System.out.println(h_line + "\t" + "added: " + msg + "\n"+ h_line); //output the added "msg"
                }
            }
        }
    }
}
