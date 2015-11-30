package libcppgen;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by k on 30.11.2015.
 */
public class OutputFileManager {
    private final File rootLocatiopn;
    private ArrayList<FileManagerEventListener> errorListeners;


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
        this.rootLocatiopn = root;
        errorListeners = new ArrayList<>();
    }



}
