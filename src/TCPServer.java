import java.io.*; 
import java.net.*; 
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

// Server class 
class TCPServer { 
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
				System.out.println("New client connected"
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
			if (server != null) { 
				try { 
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
		private String name; 

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
					String clientSentence = in.readLine(); 
					System.out.println("Recieved: " + clientSentence);
						
					String messageType = clientSentence.substring(0,6);
					String message = clientSentence.substring(6);
					
					System.out.println(messageType);
					System.out.println("message"+message);
					if(messageType.equals("NAME--")) {
						this.name = message;
						System.out.println(name);
					}
					else if (messageType.equals("MATH--")) {
						// math calculation here
						System.out.println("Enter math");
						try
						{
							out.println(Calculator.calculate(message));
						}
						catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							out.println("Improper input");
						}
					}
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
				try { 
					out.close(); 
					in.close(); 
					this.clientSocket.close(); 
					System.out.println("Connection Closed");
				} 
				catch (IOException e) { 
					e.printStackTrace(); 
				} 
			} 
		} 
	} 
}
