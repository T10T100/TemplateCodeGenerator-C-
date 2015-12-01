package libcppgen;

import java.util.EventObject;

/**
 * Created by k on 30.11.2015.
 */
public class FileManagerEvent extends EventObject {
    private final String cause;
    public FileManagerEvent (Object o, String cause)
    {
        super(o);
        this.cause = cause;
    }



    public String getCause ()
    {
        return cause;
    }
}
