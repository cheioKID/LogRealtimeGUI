package log;

import java.io.File;     
import java.io.IOException;     
import java.io.RandomAccessFile;     
import java.util.concurrent.Executors;     
import java.util.concurrent.ScheduledExecutorService;     
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

    
public class LogRealtimeView {  
	LogTabPane logTabPane = new LogTabPane();
    private long latestPosition = 0; //the tail of the log 
    
	public void LogRealtimePerceive(File logFile) throws IOException{     
        @SuppressWarnings("resource")
		final RandomAccessFile randomFile = new RandomAccessFile(logFile, "rw"); 
        
        // launch one thread 
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);    
        exec.scheduleWithFixedDelay(new Runnable(){     
            public void run() {     
                try {     
                    randomFile.seek(latestPosition); //random access     
                    String line = null;     
                    while( (line = randomFile.readLine())!= null) {   
                    	//occur LogContentEvent
                    	String alog = new String(line.getBytes("ISO8859-1"));
                    	logTabPane.suggestLogContentChangedEvent(alog + "\n");
                        System.out.println(alog);     
                    }     
                    latestPosition = randomFile.length();
                } catch (IOException e) {     
                    throw new RuntimeException(e);     
                }     
            }     
        }, 0, 1, TimeUnit.SECONDS);     
    } 
    
    
         
    public static void main(String[] args) throws Exception { 
    	LogRealtimeView view = new LogRealtimeView();     
        final File hello = new File("hello.log"); 
    	
    	JFrame frame = new JFrame(); 
		frame.getContentPane().add(view.logTabPane); 
		frame.setSize(500,400); 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		try {
			System.out.println(Thread.currentThread().getName()+" sleep(3000)"); 
			Thread.sleep(3000); 
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		logTabPane.suggestLcc();*/
		   
        view.LogRealtimePerceive(hello);    
    }    
}    