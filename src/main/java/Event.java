public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        //super.description = super.description + "(at: " + at + ")";
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.description + "(at: " + at + ")";
    }
}