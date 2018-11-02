package log;


import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InstUnit {
	public JTextArea logtext = new JTextArea("janitor");
	JScrollPane msg = new JScrollPane(logtext);
	
	public JScrollPane getMsg() {
		return msg;
	}

	public void setLog(String log) {
		logtext.setText(log);
	}
	
	public void appendLog(String log) {
		logtext.append(log);
	}

	public InstUnit(String s) {
		// TODO Auto-generated constructor stub
		logtext.setText(s);
	}
	
	public InstUnit() {
		// TODO Auto-generated constructor stub
	}
}
