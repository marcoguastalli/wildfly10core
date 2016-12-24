package robots;

public enum RobotExecutionResult {

    OK(0),
    WARNING(1),
    CRITICAL(2),
    UNKNOWN(3);

    private final int value;

    RobotExecutionResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
