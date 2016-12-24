package robots;

import org.apache.log4j.Logger;
import service.FileService;
import service.FileServiceBean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PrintFileSystem implements Robot {
    private static final Logger logger = Logger.getLogger(PrintFileSystem.class);
    private static final Map<String, String> disksToPrint = new LinkedHashMap<String, String>();

    static {
        //disksToPrint.put("/media/WDB1/wdb1", "/opt/WDB1.txt");
        //disksToPrint.put("/media/WDB2/wdb2", "/opt/WDB2.txt");
        //disksToPrint.put("/media/WDB3/wdb3", "/opt/WDB3.txt");
        //disksToPrint.put("/media/SEAG02/seag02", "/opt/SEAG02.txt");
        //disksToPrint.put("/media/SAM500/sam500", "/opt/SAM500.txt");
        //disksToPrint.put("/media/DISCOWD/discowd", "/opt/DISCOWD.txt");
        //disksToPrint.put("/media/WD15EXT4/wd15ext4", "/opt/WD15EXT4.txt");
        //disksToPrint.put("/media/WDGREEN10/wdgreen10", "/home/marco27/Dropbox/DevStudioIII/PrintFileSystem/WDGREEN10.txt");
        disksToPrint.put("c:\\temp\\tra\\", "c:\\temp\\tra\\tra.txt");
    }

    private FileService fileService;

    public PrintFileSystem() {
            fileService = new FileServiceBean();
    }

    public static void main(String[] args) {
        Robot robot = new PrintFileSystem();
        RobotExecutionResult robotExecutionResult = robot.execute(null);
        logger.info("Robot result " + robotExecutionResult);
    }

    public RobotExecutionResult execute(Map<String, String> map) {
        RobotExecutionResult result = RobotExecutionResult.CRITICAL;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            for (Map.Entry<String, String> entry : disksToPrint.entrySet()) {
                String dirToPrint = entry.getKey();
                String fileToSave = entry.getValue();

                logger.info("Printing directory " + dirToPrint + " into file " + fileToSave + "...");

                fileWriter = new FileWriter(new File(fileToSave));
                bufferedWriter = new BufferedWriter(fileWriter);

                fileService.printDirStructure(bufferedWriter, dirToPrint, fileToSave);

                logger.info("Printed directory " + dirToPrint + " into file " + fileToSave);
                result = RobotExecutionResult.OK;
            }
        } catch (IOException e) {
            logger.error("Error!", e);
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                logger.error("Error!", e);
            }
        }
        return result;
    }


    public void interrupt() {

    }
}
