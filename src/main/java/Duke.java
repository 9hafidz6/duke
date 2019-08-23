import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String h_line = "_____________________________________\n\n";
        String greet_msg = "Hello! i'm Duke\n"
                + "What can i do for you?\n";
        String bye_msg = "Bye. Hope to see you again soon!";
        String msg = "";

        String[] task_list = new String[100];

        System.out.println(h_line + greet_msg + h_line);

        while(true) {
            msg = sc.nextLine();
            if(msg.equals("bye")) { //if user input bye message, end the program
                System.out.println(h_line + "\t" + bye_msg + "\n"+ h_line);
                break;
            }
            else if(msg.equals("list")) { //if user enters the command list
                System.out.println(h_line);
                for(int a = 0; a < 100; a++) {
                    if(task_list[a] == null) {
                        break;
                    }
                    System.out.println(a + ". " + task_list[a] + "\n"); //outputs all entry of list from 1 ~ 100 if have
                }
                System.out.println(h_line);
            }
            else { //if user enters a command, add into task_list
                boolean flag = false; //flag to check whether task exists
                for(int a = 0; a < 100; a++) {
                    if(task_list[a] == null) { //if list is empty, break and add the item into list
                        break;
                    }
                    if(task_list[a].equals(msg)) { //if item has already been added
                        flag = true;
                        break;
                    }
                }
                if(flag) { //if task already exist, output msg has already been added
                    System.out.println(h_line + "\t" + msg + " has already been added" + "\n"+ h_line);
                }
                else {
                    for(int a = 0; a < 100; a++) {
                        //need to go through all the array
                        if(task_list[a] == null) { //if encounters the first empty array
                            task_list[a] = msg;
                            break;
                        }
                    }
                    System.out.println(h_line + "\t" + "added: " + msg + "\n"+ h_line); //output the added "msg"
                }
            }
        }
    }
}
