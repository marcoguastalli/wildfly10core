package apps.fsprint.service;

public class FileSystemPrintServiceBean implements FileSystemPrintService {

    @Override
    public String print(String name) {
        return "FILE " + name + " PRINT";
    }
}
