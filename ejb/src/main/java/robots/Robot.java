package robots;

public interface Robot {
    RobotExecutionResult execute(java.util.Map<java.lang.String, java.lang.String> map);

    void interrupt();
}
