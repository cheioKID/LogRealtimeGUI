package log;

import java.util.EventListener;


public interface LogContentListener extends EventListener {
    /**
      * Called whenever the value of the selection changes.
      * @param e the event that characterizes the change.
      */
    void logContentChanged(LogContentEvent e);
}
