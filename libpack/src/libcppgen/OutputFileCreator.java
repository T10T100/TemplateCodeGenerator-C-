package libcppgen;

import java.io.File;

/**
 * Created by k on 30.11.2015.
 */
public class OutputFileCreator {
    private final File file;
    private String name;
    private String extension;


    public OutputFileCreator (File file)
    {
        this.file = file;
        this.name = "DefaultFileName";
        this.extension = ".cpp";
    }

    public OutputFileCreator (File file, String name, String extension)
    {
        this.file = file;
        this.name = name;
        this.extension = extension;
    }
}
