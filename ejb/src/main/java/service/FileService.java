package service;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {

    void printDirStructure(BufferedWriter oBufferedWriter, String dirToPrint, String fileToSave) throws IOException;

    List<File> listAndSortDirectoryFiles(File path);

    void createFileFromString(File outputFile, String str);
}
