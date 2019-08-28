public class Task {
    protected String description;
    protected boolean isDone;
    protected  String type;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markDone(){
        isDone = true;
    }
    public String getStatusIcon(){
        return (isDone ? "✔" : "✘"); //return tick or X symbols
    }
}
