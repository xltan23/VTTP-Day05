package vttp.d5;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        
        String host = "localhost";
        int port = 3000;
        if (args.length>=2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        // Connect to server
        System.out.printf("Connect to server %s on port %s\n", host, port);
        Socket sock = new Socket(host, port);
        System.out.println("Connected...");

        NetworkIO netIO = new NetworkIO(sock);
        Console cons = System.console();
        String request = "";
        String response = "";

        while (!request.equals("exit")) {
            request = cons.readLine(">> ");
            if (request.trim().equals("exit")) {
                break;
            }
            netIO.write(request);
            response = netIO.read();
            System.out.printf(">> %s\n", response);
        }
        
        netIO.close();
        sock.close();

        System.out.println("Terminating client...");
    }
}