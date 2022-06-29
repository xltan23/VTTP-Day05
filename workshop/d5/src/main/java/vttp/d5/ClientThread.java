package vttp.d5;

import java.io.IOException;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket sock;

    public ClientThread(Socket s) {
        sock = s;
    }

    @Override
    public void run() {
        System.out.println("Starting client thread");
        try {
            NetworkIO netIO = new NetworkIO(sock);
            String request = "";
            while (!request.equals("exit")) {
                try {
                    request = netIO.read();
                    System.out.printf("[Client] %s\n", request);
                    if (request.trim().equals("exit")) {
                        break;
                    }
                    netIO.write(request.toUpperCase());
                } catch (IOException e) {
                    // Exit when connection established
                    break;
                }
            }

            netIO.close();
            sock.close();
            System.out.println("Exiting thread");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
