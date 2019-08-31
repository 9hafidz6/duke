import javax.print.DocFlavor;
import java.lang.module.InvalidModuleDescriptorException;
import java.util.Scanner;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.io.*;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FileRW file = new FileRW();
        SimpleDateFormat F_date = new SimpleDateFormat("dd/MM/yyyy HHmm");

        String partition = "_____________________________________\n";
        String greet_msg = "Hello! i'm Duke\n" + "What can i do for you?\n";
        String bye_msg = "Bye. Hope to see you again soon!";
        String input;
        String[] token;
        ArrayList<Task> task_list = new ArrayList<Task> (100);
        task_list = file.GetData();

        System.out.println(partition + greet_msg + partition);

        while(true) {
            input = sc.nextLine();
            if (input.equals("bye")) { //user enters the bye command
                file.requestToWriteTheFile(task_list);
                System.out.println(partition + "\t" + bye_msg + "\n"+ partition);
                break;
            }
            else if (input.equals("list")) { //user enters the list command
                System.out.println(partition);
                if(task_list.size() == 0) {
                    System.out.println("list is empty");
                }
                for(int a = 0; a < task_list.size(); a++) { //outputs all entry of list
                    System.out.println((a+1) + ". " + task_list.get(a).toString() + "\n");
                }
                System.out.println(partition);
            }
            else if (input.indexOf("done") == 0) { //user enters done command
                System.out.println(partition);
                try{
                    token = input.split(" ");
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
            else if (input.indexOf("delete") == 0) { //deletes a task in the list
                System.out.println(partition);
                try {
                    token = input.split(" ");
                    if(task_list.size() < Integer.parseInt(token[1])) {
                        System.out.println("Task number " + token[1] + " does not exist in the list, " + task_list.size() + " tasks left in your list");
                    }
                    else {
                        System.out.println("Noted. I've removed this task: \n");
                        System.out.println(task_list.get(Integer.parseInt(token[1]) - 1) + "\n");
                        task_list.remove(Integer.parseInt(token[1]) -1);
                        System.out.println("you have " + task_list.size() + " tasks left in your list");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("enter a valid index");
                }
                System.out.println(partition);
            }
            else if (input.indexOf("find") == 0) { //finds task in list
                System.out.println(partition);
                String f_input = input.substring(5);
                boolean flag = false;
                for(int a = 0; a < task_list.size(); a++) {
                    if(String.valueOf(task_list.get(a)).contains(f_input)) {
                        if(!flag) {
                            System.out.println("Here are the matching tasks in your list:\n");
                        }
                        flag = true;
                        System.out.println((a+1) + ". " + task_list.get(a).toString() + "\n");
                    }
                }
                if (!flag) {
                    System.out.println("Sorry, could'nt find the task with ( " + f_input + " )\n");
                }
                System.out.println(partition);
            }
            else { //if user enters a command, todo, deadline and event

                boolean flag = false; //flag to check whether task exists in list

                for (Task task : task_list) {
                    if (task.description.equals(input)) { //if item has already been added, don't add
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
                            token = d_input.split(" /by ");
                            Date date = F_date.parse(token[1]);
                            String formattedDate = date.toString();
                            Task t = new Deadline(token[0], formattedDate);
                            task_list.add(t);
                            System.out.println("\tGot it. I've added this task\n\t" + t.toString() + "\n");
                            System.out.println("\tNow you have " + task_list.size() + " tasks in this list\n");
                        }
                        catch (Exception e) {
                            if(e instanceof StringIndexOutOfBoundsException) {
                                System.out.println("☹ OOPS!!! The description of deadline cannot be empty.");
                            }
                            else if (e instanceof IndexOutOfBoundsException){
                                System.out.println("☹ OOPS!!! please enter a valid Date after /by (DD/MM/YYYY).");
                            }
                            else {
                                System.out.println(e.getMessage());
                            }
                        }
                        System.out.println(partition);

                    }
                    else if(input.indexOf("event") == 0) { //if user enters event command
                        System.out.println(partition);
                        try{
                            input = input.replaceAll("\\s+", " ");
                            String e_input = input.substring(6);
                            token = e_input.split(" /at ");
                            Date date = F_date.parse(token[1]);
                            String formattedDate = date.toString();
                            Task t = new Event(token[0],formattedDate);
                            task_list.add(t);
                            System.out.println("\tGot it. I've added this task\n\t" + t.toString() + "\n");
                            System.out.println("\tNow you have " + task_list.size() + " tasks in this list\n");
                        }
                        catch (Exception e) {
                            if(e instanceof StringIndexOutOfBoundsException) {
                                System.out.println("☹ OOPS!!! The description of event cannot be empty.");
                            }
                            else if(e instanceof  IndexOutOfBoundsException) {
                                System.out.println("☹ OOPS!!! please enter a valid Date and Time after /at.");
                            }
                            else {
                                System.out.println(e.getMessage());
                            }
                        }
                        System.out.println(partition);
                    }
                    else {
                        System.out.println(partition);
                        System.out.println("☹ OOPS!!! please enter a valid command\n" +
                                            "done\t" + "list\n" + "delete\t" + "find\n" + "todo\t"
                                            + "deadline\n" + "event\t");
                        System.out.println(partition);
                    }
                }
            }
        }
    }
}
