package log;

import java.util.EventObject;

public class LogContentEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	String appendLog;
	
	public int lastLength;
	public boolean isLogContentChanged;

	public LogContentEvent(Object source, String alog) {
		super(source);
		appendLog = alog;
	}
	
	public void setAppendLog(String alog) {
		appendLog = alog;
	}
	
	
}
