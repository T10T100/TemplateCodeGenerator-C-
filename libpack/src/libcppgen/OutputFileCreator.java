package libcppgen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by k on 30.11.2015.
 */
public class OutputFileCreator {
    private final File file;
    private String name;
    private String extension;
    ArrayList<String> buffer;



    public OutputFileCreator (File file)
    {
        this.file = file;
        this.name = "DefaultFileName";
        this.extension = ".cpp";
        buffer = new ArrayList<>();
    }

    public OutputFileCreator (File file, String name, String extension)
    {
        this.file = file;
        this.name = name;
        this.extension = extension;
        buffer = new ArrayList<>();
    }
}
