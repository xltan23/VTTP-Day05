package vttp.d5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {

    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;

    public NetworkIO(Socket sock) throws IOException {
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
    }

    public String read() throws IOException {
        return dis.readUTF();
    }
    
    public String write(String message) throws IOException {
        dos.writeUTF(message);
        dos.flush();
        return message;
    }

    public void close() {
        try {
            dis.close();
            is.close();
            dos.close();
            os.close();
        } catch (IOException ex) {
            // Not important
        }
    }
}
