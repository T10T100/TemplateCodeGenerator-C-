package libcppgen;

import java.util.EventListener;

/**
 * Created by k on 30.11.2015.
 */
public interface FileManagerEventListener extends EventListener{
    void eventPerformed (FileManagerEvent e);
}
