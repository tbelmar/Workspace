package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {
    final int BUFFERSIZE = 2048;
    
    boolean shutdown;
    Integer timeout;
    Integer limit;
    
    public TCPClient(boolean shutdown, Integer timeout, Integer limit) {
        this.shutdown = shutdown;
        this.timeout = timeout;
        this.limit = limit;
    }

    public byte[] askServer(String hostname, int port, byte [] toServerBytes) throws IOException {
        byte[] byteArr = new byte[2048];
        ByteArrayOutputStream dynamicByteArr = new ByteArrayOutputStream();

        Socket s = new Socket(hostname, port);

        if(timeout != null) s.setSoTimeout(timeout);

        // handle output bytes
        OutputStream os = s.getOutputStream();
        if(toServerBytes != null) os.write(toServerBytes);
        if(shutdown) s.shutdownOutput();

        // handle input bytes
        InputStream is = s.getInputStream();
        try {
            int nOfBytes = 1;
            while((nOfBytes = is.read(byteArr, 0, (limit == null || limit > BUFFERSIZE) ? BUFFERSIZE-1 : limit)) >= 0) {
                dynamicByteArr.write(byteArr, 0, nOfBytes);
                if(limit != null) limit -= nOfBytes;
                if(limit != null && limit <= 0) break;
            }
        }
        catch(SocketTimeoutException e) {}

        s.close();

        return dynamicByteArr.toByteArray();
    }
}
