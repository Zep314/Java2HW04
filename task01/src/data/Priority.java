package data;

public class Priority {
    public final int ERROR = -1;
    public static final int LOW = 0;
    public final int MIDDLE = 1;
    public final int HIGH = 2;
    private int priority;

    Priority() {
        setPriorityLow();
    }

    Priority(int priority) {
        setPriority(priority);
    }
    public void setPriority(int priority) {
        switch (priority) {
            case LOW -> { setPriorityLow(); }
            case MIDDLE -> { setPriorityMiddle(); }
            case HIGH -> { setPriorityHigh(); }
            default -> { this.priority = ERROR;}
        }
    }

    public void setPriorityLow() {
        this.priority = LOW;
    }

    public void setPriorityMiddle() {
        this.priority = MIDDLE;
    }

    public void setPriorityHigh() {
        this.priority = HIGH;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        switch (this.priority) {
            case LOW -> { return "LOW"; }
            case MIDDLE -> { return "MIDDLE"; }
            case HIGH -> { return "HIGH"; }
            default -> { return "ERROR";}
        }
    }
}
