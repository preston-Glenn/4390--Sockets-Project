import java.io.*; 
import java.net.*; 

class TCPServer { 

  public static void main(String argv[]) throws Exception 
    { 
      String clientSentence; 
      String capitalizedSentence; 

      ServerSocket welcomeSocket = new ServerSocket(6789); 

      while(true) { 
  
            Socket connectionSocket = welcomeSocket.accept(); 

           BufferedReader inFromClient = 
              new BufferedReader(new
              InputStreamReader(connectionSocket.getInputStream())); 


           DataOutputStream  outToClient = 
             new DataOutputStream(connectionSocket.getOutputStream()); 

           clientSentence = inFromClient.readLine(); 

           capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

           outToClient.writeBytes(capitalizedSentence); 
        } 
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
