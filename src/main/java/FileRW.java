import java.io.*;
import java.util.ArrayList;

public class FileRW extends Task{
    private static Task[] data_list = new Task[100];
    private static int task_num = 0;
    ArrayList<Task> task_list = new ArrayList<Task> (100);

    public FileRW() {
        super("default");
        try {
            FileReader fileRead = new FileReader("data/duke.txt");
            BufferedReader reader = new BufferedReader(fileRead);
            String data;

            while((data = reader.readLine()) != null) {
                String[] token = data.split("@");

                Task t = new Task("default");
                if(token[0].equals("T")) {
                    t = new ToDos(token[2]);
                    task_list.add(t);
                    if(token[1].equals("1")) {
                        t.markDone();
                    }
                }
                else if(token[0].equals("D")) {
                    t = new Deadline(token[2], token[3]);
                    task_list.add(t);
                    if(token[1].equals("1")) {
                        t.markDone();
                    }
                }
                else if(token[0].equals("E")) {
                    t = new Event(token[2], token[3]);
                    task_list.add(t);
                    if(token[1].equals("1")) {
                        t.markDone();
                    }
                }
                else {
                    System.out.println("invalid file data");
                }
                data_list[task_num++] = t;
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Task> GetData() {
        return task_list;
    }
}
