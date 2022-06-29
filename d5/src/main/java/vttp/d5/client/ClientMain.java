package vttp.d5.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {

    public static void main(String[] args) throws IOException{
        System.out.println("Connecting to localhost at port 3000");
        // Connect the server (127.0.0.1 - localhost)
        Socket sock = new Socket("127.0.0.1", 3000);
        System.out.println("Connected...");

        // Get input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        
        // Get output stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        // Get input from user
        Console cons = System.console();
        String input = cons.readLine("Say something to the server: ");

        // Write to the user
        dos.writeUTF(input);
        dos.flush();

        // Wait for response from server 
        String response = dis.readUTF();
        System.out.printf(">> %s\n", response);

        // Close the stream
        is.close();
        os.close();
        // Close the socket
        sock.close();

    }
    
}
