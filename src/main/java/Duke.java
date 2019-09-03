import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {
    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage file = new Storage();
        SimpleDateFormat F_date = new SimpleDateFormat("dd/MM/yyyy HHmm");

        ArrayList<Task> task_list;
        task_list = file.GetData();

        ui.greet_msg();

        run(ui, file, F_date, task_list);
    }

    private static void run(Ui ui, Storage file, SimpleDateFormat f_date, ArrayList<Task> task_list) {
        String input;
        String[] token;
        while(true) {
            input = ui.read_command();
            token = input.split(" ");
            switch (token[0]) {
                case "bye":
                    exit(ui, file, task_list);
                    return;
                case "list":
                    show_list(ui, task_list);
                    break;
                case "done":
                    tick_list(ui, task_list, input);
                    break;
                case "delete":
                    delete_task(ui, task_list, input);
                    break;
                case "find":
                    findsTaskInList(ui, task_list, input);
                    break;
                default:
                    boolean flag = false; //flag to check whether task exists in list

                    for (Task task : task_list) {
                        if (task_list.contains(input)) { //if item has already been added, don't add
                            flag = true;
                            break;
                        }
                    }
                    if (flag) { //if task already exist, output input has already been added
                        ui.line();
                        System.out.println("\t" + input + " has already been added" + "\n");
                        ui.line();
                    }
                    else { //if task does not exist in list, add to list
                        if(input.indexOf("todo") == 0) { //if user enters todo command
                            insert_todo(ui, task_list, input);
                        }
                        else if(input.indexOf("deadline") == 0) { //if user enters deadline command
                            insert_deadline(ui, f_date, task_list, input);

                        }
                        else if(input.indexOf("event") == 0) { //if user enters event command
                            insert_event(ui, f_date, task_list, input);
                        }
                        else {
                            ui.line();
                            System.out.println("☹ OOPS!!! please enter a valid command\n" +
                                    "done\t" + "list\n" + "delete\t" + "find\n" + "todo\t"
                                    + "deadline\n" + "event\t");
                            ui.line();
                        }
                    }
            }
        }
    }

    private static void insert_event(Ui ui, SimpleDateFormat f_date, ArrayList<Task> task_list, String input) {
        String[] token;
        ui.line();
        try{
            input = input.replaceAll("\\s+", " ");
            String e_input = input.substring(6);
            token = e_input.split(" /at ");
            Date date = f_date.parse(token[1]);
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
        ui.line();
    }

    private static void insert_deadline(Ui ui, SimpleDateFormat f_date, ArrayList<Task> task_list, String input) {
        String[] token;
        ui.line();
        try {
            input = input.replaceAll("\\s+", " ");
            String d_input = input.substring(9);
            token = d_input.split(" /by ");
            Date date = f_date.parse(token[1]);
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
                System.out.println("☹ OOPS!!! please enter a valid Date after /by (DD/MM/YYYY HHmm).");
            }
            else {
                System.out.println(e.getMessage());
            }
        }
        ui.line();
    }

    private static void insert_todo(Ui ui, ArrayList<Task> task_list, String input) {
        ui.line();
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
        ui.line();
    }

    private static void findsTaskInList(Ui ui, ArrayList<Task> task_list, String input) {
        ui.line();
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
        ui.line();
    }

    private static void delete_task(Ui ui, ArrayList<Task> task_list, String input) {
        String[] token;
        ui.line();
        try {
            token = input.split(" ");
            if(task_list.size() < Integer.parseInt(token[1])) {
                System.out.println("Task number " + token[1] + " does not exist in the list, " + task_list.size() + " tasks left in your list");
            }
            else {
                System.out.println("Noted. I've removed this task: \n");
                System.out.println(task_list.get(Integer.parseInt(token[1]) - 1).toString() + "\n");
                task_list.remove(Integer.parseInt(token[1]) -1);
                System.out.println("you have " + task_list.size() + " tasks left in your list");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("enter a valid index");
        }
        ui.line();
    }

    private static void tick_list(Ui ui, ArrayList<Task> task_list, String input) {
        String[] token;
        ui.line();
        try{
            token = input.split(" ");
            if(((Integer.parseInt(token[1]) - 1) > task_list.size()) || (Integer.parseInt(token[1]) - 1) < 0) {
                System.out.println("☹ OOPS!!! Index is not in the list.");
                ui.line();
                return;
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
        ui.line();
    }

    private static void show_list(Ui ui, ArrayList<Task> task_list) {
        ui.line();
        if(task_list.size() == 0) {
            System.out.println("list is empty");
        }
        for(int a = 0; a < task_list.size(); a++) { //outputs all entry of list
            System.out.println((a+1) + ". " + task_list.get(a).toString() + "\n");
        }
        ui.line();
    }

    private static void exit(Ui ui, Storage file, ArrayList<Task> task_list) {
        file.requestToWriteTheFile(task_list);
        ui.bye_msg();
    }
}
