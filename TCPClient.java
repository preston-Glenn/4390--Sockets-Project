import java.io.*; 
import java.net.*;
import java.util.Scanner; 
class TCPClient { 

    public static void main(String argv[]) throws Exception 
    { 
        
        String Name;
        
        
        Scanner sc = new Scanner(System.in);  
		System.out.println("Hello, Welcome to Preston and Payton's Calculator Client");
		System.out.println("What is your name?");
		Name = sc.nextLine(); 
		
        try(Socket clientSocket = new Socket("127.0.0.1", 1234)){
     // writing to server
        	String check = null;
        	
 			PrintWriter out = new PrintWriter( 
 					clientSocket.getOutputStream(), true); 

 			// reading from server 
 			BufferedReader in 
 				= new BufferedReader(new InputStreamReader( 
 						clientSocket.getInputStream()));
 			out.println("NAME--"+Name); 
			out.flush();
 			
			// Get user selection.
			System.out.println("Would you like to:\nA) Enter math equation\nB) Exit");
			check = sc.nextLine();
			
			// Send server math expression and ask for input again until user disconnects
 			while("A".equalsIgnoreCase(check)) {
 				
 				
 				System.out.println("Please enter math expression");
				out.println("MATH--"+ sc.nextLine()); 
				out.flush();
				
				System.out.println("FROM SERVER: " + in.readLine()+"\n\n\n");
				System.out.println("Would you like to:\nA) Enter math equation\nB) Exit");
 				check = sc.nextLine();
 			}
 			
 			// Notify server of disconnect then disconnect.
 			out.println("CLOSE--\n");
 			out.flush();
 			clientSocket.close();
 			sc.close();
			System.out.println("Connection Closed");
        }
        
 
    } 
} 

//how do we send name in initial  connection request
//Creates connection and sends name to server

// Client 1 sends math calculation

//Client 2 creates connection
//Client 2 sends math calculation

//Client 1 sends math calculation

//Client 1 closes connection


// CLOSE--
// NAME--
// MATH--
