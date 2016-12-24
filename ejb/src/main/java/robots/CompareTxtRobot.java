package robots;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import model.Txt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompareTxtRobot {

    private final Logger logger = Logger.getLogger(CompareTxtRobot.class);

    private static final String FILE_MASTER = "/Users/marcoguastalli/001.txt";
    private static final String FILE_TO_COMPARE_TO_MASTER = "/Users/marcoguastalli/002.txt";
    private static final String FILE_WITH_DIFFERENCES = "/Users/marcoguastalli/003.txt";

    public void run() {
        logger.info("Start");
        logger.info("Comparing file " + FILE_TO_COMPARE_TO_MASTER + " with master file " + FILE_MASTER);
        try {
            Txt txtMaster = new Txt(FILE_MASTER);
            Txt txtCompared = new Txt(FILE_TO_COMPARE_TO_MASTER);
            File file = new File(FILE_WITH_DIFFERENCES);
            FileUtils.writeLines(file, getDifferences(txtMaster, txtCompared));
        } catch (IOException e) {
            logger.error("Error", e);
        }
        logger.info("Comparing result " + FILE_WITH_DIFFERENCES);
        logger.info("End");
    }

    private List<String> getDifferences(Txt txtMaster, Txt txtCompared) {
        List<String> result = new ArrayList<String>();
        Iterator<String> iterator = txtMaster.getFileContent().iterator();
        while (iterator.hasNext()) {
            String text = iterator.next();
            if (!txtCompared.getFileContent().contains(text)) {
                result.add(text);
            }
        }
        return result;
    }

}
