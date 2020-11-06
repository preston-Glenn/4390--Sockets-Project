import java.io.*; 
import java.net.*; 
class TCPClient { 

    public static void main(String argv[]) throws Exception 
    { 
        String sentence; 
        String modifiedSentence; 

        BufferedReader inFromUser = 
          new BufferedReader(new InputStreamReader(System.in)); 

        Socket clientSocket = new Socket("127.0.0.1", 6789); 

        DataOutputStream outToServer = 
          new DataOutputStream(clientSocket.getOutputStream()); 
        
        BufferedReader inFromServer = 
                new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream())); 

              sentence = inFromUser.readLine(); 

              outToServer.writeBytes(sentence + '\n'); 

              modifiedSentence = inFromServer.readLine(); 

              System.out.println("FROM SERVER: " + modifiedSentence); 

              clientSocket.close(); 
                         
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
