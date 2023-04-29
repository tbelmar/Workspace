import java.net.*;

import javax.print.DocFlavor.STRING;

import java.io.*;

import tcpclient.TCPClient;

public class HTTPAsk {
    public static final String STATUS_OK = "HTTP/1.1 200 OK\r\n\r\n";
    public static final String STATUS_NOT_FOUND = "HTTP/1.1 404 Not Found\r\n";
    public static final String STATUS_BAD_REQUEST = "HTTP/1.1 400 Bad Request\r\n";

    final static int BUFFERSIZE = 2048;
    public static void main( String[] args) throws IOException {
        byte[] receivingBytes = new byte[BUFFERSIZE];
        int serverPort = Integer.parseInt(args[0]);
        ServerSocket ss = new ServerSocket(serverPort);

        System.out.println("Listening for connections on port " + serverPort + "...");

        while(true) {
            String hostname = null;
            Integer port = null;
            byte[] string = null;
            Integer timeout = null;
            Integer limit = null;
            Boolean shutdown = false;

            Socket s = ss.accept();
            System.out.println("Client connected.");
            
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();

            is.read(receivingBytes, 0, BUFFERSIZE-1);
            String[] request = new String(receivingBytes).split("\n");

            String[] header = request[0].split("[? &=/]");
            //System.out.println(new String(receivingBytes));
            if(header[0].startsWith("GET") && header[header.length - 2].startsWith("HTTP") && header[header.length - 1].startsWith("1.1")) {
                if(header[2].startsWith("ask")) {
                    for(int i = 0; i < header.length; i++) {
                        switch(header[i]) {
                            case "hostname":
                                hostname = header[i+1];
                            break;
                            case "port":
                                port = Integer.parseInt(header[i+1]);
                            break;
                            case "string":
                                string = header[i+1].getBytes();
                            break;
                            case "shutdown":
                                shutdown = Boolean.parseBoolean(header[i+1]);
                            break;
                            case "timeout":
                                timeout = Integer.parseInt(header[i+1]);
                            break;
                            case "limit":
                                limit = Integer.parseInt(header[i+1]);
                            break;
                        }
                    }
                }
                else {
                    os.write(STATUS_NOT_FOUND.getBytes());
                    s.close();
                }
                try {
                    TCPClient tcpclient = new TCPClient(shutdown, timeout, limit);
                    byte[] serverBytes = tcpclient.askServer(hostname, port, string);
                    os.write(STATUS_OK.getBytes());
                    os.write(serverBytes);
                    s.close();
                }
                catch(IOException e) {
                    os.write(STATUS_NOT_FOUND.getBytes());
                    s.close();
                }
            }
            else {
                os.write(STATUS_BAD_REQUEST.getBytes());
                s.close();
            }
        }
    }
}

