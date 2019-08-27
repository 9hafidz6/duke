import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String partition = "_____________________________________\n";
        String greet_msg = "Hello! i'm Duke\n" + "What can i do for you?\n";
        String bye_msg = "Bye. Hope to see you again soon!";
        String input;
        ArrayList<Task> task_list = new ArrayList<Task> (100);

        System.out.println(partition + greet_msg + partition);
        while(true) {
            input = sc.nextLine();
            if (input.equals("bye")) { //user enters the bye command
                System.out.println(partition + "\t" + bye_msg + "\n"+ partition);
                break;
            }
            else if (input.equals("list")) { //user enters the list command
                System.out.println(partition);
                for(int a = 0; a < task_list.size(); a++) { //outputs all entry of list
                    System.out.println((a+1) + ". " + task_list.get(a).toString() + "\n");
                }
                System.out.println(partition);
            }
            else if (input.indexOf("done") == 0) { //user enters done command
                System.out.println(partition);
                try{
                    String[] token = input.split(" ");
                    if(((Integer.parseInt(token[1]) - 1) > task_list.size()) || (Integer.parseInt(token[1]) - 1) < 0) {
                        System.out.println("☹ OOPS!!! Index is not in the list.");
                        System.out.println(partition);
                        continue;
                    }
                    task_list.get(Integer.parseInt(token[1]) - 1).markDone();
                    System.out.println("\tNice! I've marked this task as done:\n");
                    System.out.println("\t" + task_list.get(Integer.parseInt(token[1]) - 1).toString() + "\n");
                }
                catch (NumberFormatException | IndexOutOfBoundsException e) {
                    //System.out.println(e.getMessage());
                    if( e instanceof NumberFormatException) {
                        System.out.println("☹ OOPS!!! Please enter a valid number.");
                    }
                    else if( e instanceof IndexOutOfBoundsException) {
                        System.out.println("☹ OOPS!!! The description of done cannot be empty.");
                    }
                }
                System.out.println(partition);
            }
            else { //if user enters a command, add into task_list

                boolean flag = false; //flag to check whether task exists in list

                for(int a = 0; a < task_list.size(); a++) {
                    if (task_list.get(a).description.equals(input)) { //if item has already been added, don't add
                        flag = true;
                        break;
                    }
                }
                if (flag) { //if task already exist, output input has already been added
                    System.out.println(partition);
                    System.out.println("\t" + input + " has already been added" + "\n");
                    System.out.println(partition);
                }

                else { //if task does not exist in list, add to list
                    if(input.indexOf("todo") == 0) { //if user enters todo command
                        System.out.println(partition);
                        try{
                            input = input.replaceAll("\\s+", " ");
                            String t_input = input.substring(5);
                            Task t = new ToDos(t_input);
                            task_list.add(t);
                            System.out.println("\tGot it. I've added this task\n\t" + t.toString());
                            System.out.println("\tNow you have " + task_list.size() + " tasks in this list\n");
                        }
                        catch (IndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of todo cannot be empty.");
                        }
                        System.out.println(partition);
                    }
                    else if(input.indexOf("deadline") == 0) { //if user enters deadline command
                        System.out.println(partition);
                        try {
                            input = input.replaceAll("\\s+", " ");
                            String d_input = input.substring(9);
                            String[] token = d_input.split("/by");
                            Task t = new Deadline(token[0], token[1]);
                            if(token[0].isBlank()) {
                                System.out.println("☹ OOPS!!! /by cannot be empty.");
                                continue;
                            }
                            if(token[1].isBlank()) {
                                System.out.println("☹ OOPS!!! /by cannot be empty.");
                                continue;
                            }
                            task_list.add(t);
                            System.out.println("\tGot it. I've added this task\n\t" + t.toString() + "\n");
                            System.out.println("\tNow you have " + task_list.size() + " tasks in this list\n");
                        }
                        catch ( ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                            if(e instanceof StringIndexOutOfBoundsException) {
                                System.out.println("☹ OOPS!!! The description of deadline cannot be empty.");
                            }
                        }
                        System.out.println(partition);

                    }
                    else if(input.indexOf("event") == 0) { //if user enters event command
                        System.out.println(partition);
                        try{
                            input = input.replaceAll("\\s+", " ");
                            String e_input = input.substring(6);
                            String[] token = e_input.split("/at");
                            Task t = new Event(token[0],token[1]);
                            task_list.add(t);
                            System.out.println("\tGot it. I've added this task\n\t" + t.toString() + "\n");
                            System.out.println("\tNow you have " + task_list.size() + " tasks in this list\n");
                        }
                        catch (Exception e) {
                            System.out.println("invalid Event command " + e.getMessage());
                        }
                        System.out.println(partition);
                    }
                }
            }
        }
    }
}
