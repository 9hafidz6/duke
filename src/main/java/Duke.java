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

    /**
     * read commands from the user ui.read_command(). then tokenize the command with respect to spaces.
     * implemented a switch case based on the first token. switch case will then execute different functions
     * of the program. if no such command is found in the switch case, output default message that asks users
     * to enter valid commands
     *
     * @param ui prints partition line, greet, bye, default messages as well as reading next user command
     * @param file reads and write data the is on the project root
     * @param f_date object to store the formatted date
     * @param task_list ArrayList to store all the tasks
     */
    private static void run(Ui ui, Storage file, SimpleDateFormat f_date, ArrayList<Task> task_list) {
        String input;
        String[] token;
        while(true) {
            input = ui.read_command();
            token = input.split(" ");
            Parser parser = new Parser(input);
            switch (token[0]) {
                case "bye":
                    Parser.exit(ui, file, task_list);
                    return;
                case "list":
                    Parser.show_list(ui, task_list);
                    break;
                case "done":
                    Parser.tick_list(ui, task_list, input);
                    break;
                case "delete":
                    Parser.delete_task(ui, task_list, input);
                    break;
                case "find":
                    Parser.findsTaskInList(ui, task_list, input);
                    break;
                case "todo":
                    Parser.insert_todo(ui, task_list, input);
                    break;
                case "deadline":
                    Parser.insert_deadline(ui, f_date, task_list, input);
                    break;
                case "event":
                    Parser.insert_event(ui, f_date, task_list, input);
                    break;
                default:
                    ui.default_msg();
            }
        }
    }
}