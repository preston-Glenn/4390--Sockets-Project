# 4390--Sockets-Project

## Description
This project uses TCP sockets to create a client/server system. The client will connect and create a socket. It will then send a name for the server to identify each client with. The client can then send basic operators without order of operations and precedence. The server then calculates the request and returns it to the client. In order to achieve true multiple simultaneous connections, threads were used on the server side with one thread dedicated to each client connection.

## Running

### Makefile and Compilation
The makefile will compile the project as well as remove any class files once complete.
To compile the project cd into the project directory and type "make"
To clean the directory remain in the project directory and type "make clean"

### Server
To Start the server type `java TCPServer` into the cmd and it will begin running. It will log its operations to both the console and a log.txt file.

### Client
To start the client type `java TCPClient` into the cmd and it will beginning running. Enter desired input as the prompts appear and disconnect at anytime.
