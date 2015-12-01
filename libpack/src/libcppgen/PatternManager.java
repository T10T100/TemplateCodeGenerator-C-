package libcppgen;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by k on 30.11.2015.
 */
public class PatternManager {
    private ArrayList<File> patterns;

    public PatternManager ()
    {
        patterns = new ArrayList<>();
    }




    public void searchThereForPatterns (File location)
    {
        if (location.exists() == false) {
            return;
        }
        File file;
        patterns.clear();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(location.toPath())) {
            for (Path p : stream) {
                file = p.toFile();
                if (file.isDirectory() == true) {
                    if (file.getName().contains("patt") == true) {
                        patterns.add(file);
                    }
                }
            }
        } catch (IOException e) {

        }
    }


    public ArrayList<File> getPatterns ()
    {
        return patterns;
    }
}
