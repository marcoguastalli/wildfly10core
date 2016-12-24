package service;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.comparator.PathFileComparator;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Stateless
@Local(FileService.class)
public class FileServiceBean implements FileService {

    public void printDirStructure(BufferedWriter bufferedWriter, String dirToPrint, String fileToSave) throws IOException {
        List<File> filesInDirectorySorted = listAndSortDirectoryFiles(new File(dirToPrint));
        for (int i = 0; i < filesInDirectorySorted.size(); i++) {
            File oFile = filesInDirectorySorted.get(i);
            if (oFile.isDirectory()) {
                bufferedWriter.write("\n[" + oFile.getAbsolutePath() + "]");
                printDirStructure(bufferedWriter, oFile.getAbsolutePath(), fileToSave);
            } else {
                bufferedWriter.write("\n" + oFile.getAbsolutePath());
            }
        }
    }

    public List<File> listAndSortDirectoryFiles(File path) {
        List<File> result = new ArrayList<File>();
        if (path != null && path.isDirectory()) {
            //result = Arrays.asList(path.listFiles((java.io.FilenameFilter) org.apache.commons.io.filefilter.FileFileFilter.FILE));
            result = Arrays.asList(path.listFiles());
            Collections.sort(result, PathFileComparator.PATH_SYSTEM_COMPARATOR);
        }
        return result;
    }

    public void createFileFromString(File file, String str) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            IOUtils.write(str, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileWriter);
        }
    }

}
