package log;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class LogTabPane extends JTabbedPane implements LogContentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InstUnit iunit = new InstUnit();
	
	public LogTabPane() {
		super();
		this.add("hello", iunit.getMsg());
	}
	

	
	public synchronized void addLogContentListener(LogContentListener lcl) {
		listenerList.add(LogContentListener.class, lcl);
	}
	

	@Override
	public void logContentChanged(LogContentEvent e) {
		System.out.println("append log changes");
		iunit.appendLog(e.appendLog);
		
	}
	
	public void suggestLogContentChangedEvent(String alog) {
		System.out.println("suggest event occurs");
		logContentChanged(new LogContentEvent(this, alog));
	}
	
	public static void main(String args[]){ 
		JFrame f =new JFrame(); 
		LogTabPane logTabPane =new LogTabPane(); 
		f.getContentPane().add(logTabPane); 
		f.setSize(500,400); 
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			System.out.println(Thread.currentThread().getName()+" sleep(3000)"); 
			Thread.sleep(3000); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		logTabPane.suggestLogContentChangedEvent("goodbye");
	}
}

