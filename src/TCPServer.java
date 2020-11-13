import java.io.*; 
import java.net.*; 



class ClientHandler implements Runnable{  
	private Socket clientSocket;
	final BufferedReader inFromClient;
	final DataOutputStream outToClient;
	private String name;
	  
	ClientHandler(Socket clientSocket, BufferedReader in, DataOutputStream out) {
		this.clientSocket = clientSocket;
		this.inFromClient = in;
		this.outToClient = out;
	  } 
	  
	public void run() {
		try {
			// Input/Output streams
			while(true) {
				String clientSentence = this.inFromClient.readLine(); 
				System.out.println("Recieved: " + clientSentence);
					
				String messageType = clientSentence.substring(0,6);
				String message = clientSentence.substring(6);
				System.out.println(messageType);
				System.out.println(message);
				if(messageType.equals("NAME--")) {
					this.name = message;
					System.out.println(name);
				}
				else if (messageType.equals("MATH--")) {
					// math calculation here
					System.out.println("ENter math");
					this.outToClient.writeBytes(message);
				}
				else {
					// close connection
					System.out.println("Connection Closed");
					this.clientSocket.close();
					break;
				}
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
        try
        { 
            // closing resources 
            this.inFromClient.close(); 
            this.outToClient.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
		
	}
}

class TCPServer { 
	 private static final int PORT_NUMBER = 6789;
	    private static ServerSocket serverSocket;
	    private static ClientHandler clientHandler;
	    private static Thread thread;

	    public static void main(String[] args) throws IOException {
	        serverSocket = new ServerSocket(PORT_NUMBER);

	        while (true) {
	        	Socket s = null;
	        	try {
	        		
	        		s = serverSocket.accept();
	        		
	        		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					DataOutputStream  out = new DataOutputStream(s.getOutputStream()); 
					
		            clientHandler = new ClientHandler(s, in, out);
		            thread = new Thread(clientHandler);
		            thread.start();
		            
	        	}
	        	catch (Exception e) {
	        		s.close();
	        		e.printStackTrace();
	        	}

	        }
	    }

	    protected void finalize() throws IOException {
	        serverSocket.close();
	    }
} 
 
// Client 1 send connection request-- Server creats connection and logs name of user

//Client 1 sends MATH request-- Server logs request--Server sends answer


//Client 2 send connection request-- Server creats connection and logs name of user
//Client 2 sends MATH request-- Server logs request--Server sends answer
           

//Client 1 sends MATH request-- Server logs request--Server sends answer
//Client 1 sends MATH request-- Server logs request--Server sends answer

//Client 2 closes connection--Server closes connection--Server logs closeing


//LOGS
// [TIME STANP]: ACTION TYPE-- USER INFO
