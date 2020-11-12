import java.io.*; 
import java.net.*; 
class TCPClient { 

    public static void main(String argv[]) throws Exception 
    { 
        
        String modifiedSentence;
        String Name;
        
        Boolean Continue = true;
        
        BufferedReader inFromUser = 
          new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Hello, Welcome to Preston and Payton's Calculator Client");
		System.out.println("What is your name?");
		Name = inFromUser.readLine(); 
		
        Socket clientSocket = new Socket("127.0.0.1", 6789); 
        DataOutputStream outToServer = 
          new DataOutputStream(clientSocket.getOutputStream()); 
        
        BufferedReader inFromServer = 
                new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream())); 
        outToServer.writeBytes("NAME--"+Name + '\n'); 
        
        while(Continue) {
        	String expression; 
        	String check = "";
        	System.out.println("Would you like to:\nA) Enter math equation\nB) Exit");
        	check = inFromUser.readLine();
        	
        	switch(check) {
	        	case "A":
	        		break;
	        	default:
	        		outToServer.writeBytes("CLOSE--\n");
	    			Continue = false;
	    			System.out.println("Connection Closed");
	    			continue;
        	}
        	try {
		    	System.out.println("Please enter math expression");
		    	expression = inFromUser.readLine(); 
				outToServer.writeBytes("MATH--"+expression + '\n'); 
				String answer = inFromServer.readLine(); 
				System.out.println("FROM SERVER: " + answer+"\n\n\n"); 
        	}
        	catch(Exception e){
        		outToServer.writeBytes("CLOSE--\n");
    			Continue = false;
    			System.out.println("Connection Closed due to "+e);
    			continue;	
        	}	
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
