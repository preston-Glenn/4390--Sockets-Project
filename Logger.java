import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    


public class Logger {
	private File log;
	private FileWriter fileWriter;
	private PrintWriter output;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	
	// Constructor w/ custom file name.
	Logger(String fileName){
		this.log = new File(fileName);
		try {
			fileWriter = new FileWriter(log.getName());
			output = new PrintWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Default constructor
	Logger(){
		this.log = new File("log.txt");
		try {
			fileWriter = new FileWriter(log.getName());
			output = new PrintWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Change file name.
	public void setLogFile(String fileName) {
		this.log = new File(fileName);
		try {
			fileWriter = new FileWriter(log.getName());
			output = new PrintWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// log message
	public void log(String message) {
		
		LocalDateTime now = LocalDateTime.now(); 
		
		output.println("[" + dtf.format(now) + "] " + message);
		System.out.println("[" + dtf.format(now) + "] " + message);
		output.flush();
	}
	
	// close output stream to file.
	public void close() {
		output.close();
	}
}
