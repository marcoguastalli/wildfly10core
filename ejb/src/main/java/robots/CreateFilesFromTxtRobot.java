package robots;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import model.Txt;
import service.FileService;
import service.FileServiceBean;

public class CreateFilesFromTxtRobot implements Robot {
    private static final Logger logger = Logger.getLogger(CreateFilesFromTxtRobot.class);

    private static final Map<String, String> parameterMap = new LinkedHashMap<>();

    static {
        parameterMap.put("workingDir", "C:\\temp\\dapi\\");
        parameterMap.put("inputTxtFile", "dapi.xml");
    }

    private FileService fileService;

    public CreateFilesFromTxtRobot() {
        this.fileService = new FileServiceBean();
    }

    public static void main(String[] args) {
        Robot robot = new CreateFilesFromTxtRobot();
        RobotExecutionResult robotExecutionResult = robot.execute(parameterMap);
        logger.info("Robot result " + robotExecutionResult);
    }

    public RobotExecutionResult execute(Map<String, String> parameterMap) {
        RobotExecutionResult result = RobotExecutionResult.CRITICAL;
        String workingDir = parameterMap.get("workingDir");

        Txt txt = new Txt(workingDir + parameterMap.get("inputTxtFile"));
        List<String> fileContent = txt.getFileContent();
        for (int i = 0; i < fileContent.size(); i++) {
            String outputFilePathAndName = workingDir + i + parameterMap.get("inputTxtFile") + System.getProperty("file.separator");
            File outputFile = new File(outputFilePathAndName);

            String str = fileContent.get(i);

            this.fileService.createFileFromString(outputFile, str);
        }

        result = RobotExecutionResult.OK;

        return result;
    }

    public void interrupt() {

    }
}
