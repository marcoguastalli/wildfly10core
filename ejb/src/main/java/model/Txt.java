package model;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Txt {
    private static final String ENCODING_UTF8 = "UTF-8";
    private String filePathAndName;
    private List<String> fileContent;

    public Txt(String filePathAndName) {
        this.filePathAndName = filePathAndName;
        try {
            loadFileContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFileContent() throws IOException {
        InputStream inputStream = new FileInputStream(new File(filePathAndName));
        fileContent = IOUtils.readLines(inputStream, ENCODING_UTF8);
        IOUtils.closeQuietly(inputStream);
    }

    public List<String> getFileContent() {
        return fileContent;
    }
}
