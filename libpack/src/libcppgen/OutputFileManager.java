package libcppgen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by k on 30.11.2015.
 */
public class OutputFileManager {
    private final File rootLocation;
    private ArrayList<FileManagerEventListener> errorListeners;
    private ArrayList<File> outputFiles;
    private ArrayList<File> outputDirs;


    private void fireErrorEvent (String message)
    {
        if (errorListeners.isEmpty() == true) {
            return;
        }
        synchronized (this) {
            ArrayList<FileManagerEventListener> V = (ArrayList) errorListeners.clone();
        }
        FileManagerEvent e = new FileManagerEvent(this, message);
        for (FileManagerEventListener l : errorListeners) {
            l.eventPerformed(e);
        }
    }
    public void addErrorListener (FileManagerEventListener l)
    {
        if (errorListeners.contains(l) == false) {
            errorListeners.add(l);
        }
    }
    public void removeErrorListener (FileManagerEventListener l)
    {
        if (errorListeners.contains(l) == true) {
            errorListeners.remove(l);
        }
    }
    public void removeAllErrorListeners ()
    {
        errorListeners.clear();
    }

    public OutputFileManager (File root)
    {
        this.rootLocation = root;
        errorListeners = new ArrayList<>();
        outputFiles = new ArrayList<>();
        outputDirs = new ArrayList<>();
    }



    public OutputFileCreator addFileCreator (String name, String extension)
    {
        String p = rootLocation.toPath().toString() + File.separator + name + "." + extension;
        File file = new File(p);
        if (file.exists() == true) {
            fireErrorEvent("File : \"" + file.getName() + "\" already done !");
        }
        try {
            file.createNewFile();
        } catch (IOException exception) {
            fireErrorEvent("Cannot create file there : \"" + rootLocation.toPath().toString() + "\" !");
            return null;
        }
        if (outputFiles.contains(file) == false) {
            outputFiles.add(file);
        }
        return new OutputFileCreator(file, name, extension);
    }



    public void cleanUp ()
    {
        for (File f : outputFiles) {
            try {
                Files.deleteIfExists(f.toPath());
            } catch (IOException e) {
                fireErrorEvent("File : \"" + f.getName() + "\" does not exist");
            }
        }
        for (File f : outputDirs) {
            try {
                Files.deleteIfExists(f.toPath());
            } catch (IOException e) {
                fireErrorEvent("Directory : \"" + f.getName() + "\" does not exist");
            }
        }
    }
}
