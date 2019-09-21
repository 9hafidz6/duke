import java.io.*;
import java.util.ArrayList;

public class Storage extends Task{
    private ArrayList<Task> task_list = new ArrayList<Task>(100);

    public Storage() {
        super("default");
        try {
            FileReader fileRead = new FileReader("../data/duke.txt");
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
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ArrayList<Task> GetData() {

        return task_list;
    }
    void requestToWriteTheFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter("../data/duke.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("");
            for (Task t : taskList) {
                if (t instanceof ToDos) {
                    if (t.isDone)
                        writer.write(t.type + "@1@" + t.description + "\n");
                    else
                        writer.write(t.type + "@0@" + t.description + "\n");
                } else if (t instanceof Event) {
                    if (t.isDone)
                        writer.write(t.type + "@1@" + t.description + "@"
                                + ((Event) t).at + "\n");
                    else
                        writer.write(t.type + "@0@" + t.description + "@"
                                + ((Event) t).at + "\n");
                } else if (t instanceof Deadline) {
                    if (t.isDone)
                        writer.write(t.type + "@1@" + t.description + "@"
                                + ((Deadline) t).by + "\n");
                    else
                        writer.write(t.type + "@0@" + t.description + "@"
                                + ((Deadline) t).by + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
