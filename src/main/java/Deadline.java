public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        //super.description = super.description + "(by: " + by + ")";
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.description + "(by: " + by + ")";
    }
}