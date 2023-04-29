package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {
    final int BUFFERSIZE = 1024;
    
    public TCPClient() {

    }

    public byte[] askServer(String hostname, int port, byte [] toServerBytes) throws IOException {
        byte[] byteArr = new byte[BUFFERSIZE];
        ByteArrayOutputStream dynamicByteArr = new ByteArrayOutputStream();

        Socket s = new Socket(hostname, port);

        OutputStream os = s.getOutputStream();
        InputStream is = s.getInputStream();

        if(toServerBytes != null) os.write(toServerBytes);
        
        int nOfBytes;

        while((nOfBytes = is.read(byteArr, 0, BUFFERSIZE-1)) >= 0) {
            dynamicByteArr.write(byteArr, 0, nOfBytes);
        }

        s.close();

        return dynamicByteArr.toByteArray();
    }
}
