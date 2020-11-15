import java.io.*; 
import java.net.*; 


// Server class 
class TCPServer { 
	
	static Logger log = new Logger();
	
	public static void main(String[] args) 
	{ 
		ServerSocket server = null; 

		try { 

			// server is listening on port 1234 
			server = new ServerSocket(1234); 
			server.setReuseAddress(true); 

			// running infinite loop for getting 
			// client request 
			while (true) { 

				// socket object to receive incoming client 
				// requests 
				Socket client = server.accept(); 

				// Displaying that new client is connected 
				// to server 
				log.log("New client connected "
								+ client.getInetAddress() 
										.getHostAddress()); 

				// create a new thread object 
				ClientHandler clientSock 
					= new ClientHandler(client); 

				// This thread will handle the client 
				// separately 
				new Thread(clientSock).start(); 
			} 
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
		finally { 
			// Close the server socket and exit the logger
			if (server != null) { 
				try { 
					log.close();
					server.close(); 
				} 
				catch (IOException e) { 
					e.printStackTrace(); 
				} 
			} 
		} 
	} 

	// ClientHandler class 
	private static class ClientHandler implements Runnable { 
		private final Socket clientSocket;
		private String name = "Unnamed"; 

		// Constructor 
		public ClientHandler(Socket socket) 
		{ 
			this.clientSocket = socket; 
		} 

		public void run() 
		{ 
			PrintWriter out = null; 
			BufferedReader in = null; 
			try { 
					
				// get the outputstream of client 
				out = new PrintWriter( 
					clientSocket.getOutputStream(), true); 

				// get the inputstream of client 
				in = new BufferedReader( 
					new InputStreamReader( 
						clientSocket.getInputStream())); 
				
				while(true) {
					// Read message from client
					String clientSentence = in.readLine(); 
					log.log("RECIEVED FROM " + name + ": " + clientSentence);
					
					// parse message into type and message
					String messageType = clientSentence.substring(0,6);
					String message = clientSentence.substring(6);
					
					// Set client name
					if(messageType.equals("NAME--")) {
						log.log("SET USERNAME: " + message);
						this.name = message;
					}
					// Perform client calculation.
					else if (messageType.equals("MATH--")) {
						log.log("SEND " + name + " Calculation: " + message);
						try
						{
							out.println(Calculator.calculate(message));
						}
						catch (Exception e) {
							e.printStackTrace();
							log.log("SEND " + name + ": Improper input");
						}
					}
					// Break the loop
					else {
						// close connection
						break;
					}
				}
					
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			}  
			finally { 
				// Close the streams, socket, and log the client disconnect.
				try { 
					out.close(); 
					in.close(); 
					this.clientSocket.close(); 
					log.log("USER " + name + " DISCONNECTED.");
				} 
				catch (IOException e) { 
					e.printStackTrace(); 
				} 
			} 
		} 
	} 
}
