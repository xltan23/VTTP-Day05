package vttp.d5.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        
        // Create a server socket and listen to a port
        ServerSocket server = new ServerSocket(3000);
        System.out.println("Waiting for connection on port 3000...");

        Socket sock = server.accept();
        System.out.println("Connection accepted");

        // Get input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        // Get output stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        // Read request from client
        String request = dis.readUTF();
        System.out.printf("Received request: %s\n", request);
        // Perform operation on request
        request = "From the server: " + request.toUpperCase();
        // Write back to client
        dos.writeUTF(request);

        // Close the stream
        is.close();
        os.close();
        // Close the socket
        sock.close();
    }
    
}
