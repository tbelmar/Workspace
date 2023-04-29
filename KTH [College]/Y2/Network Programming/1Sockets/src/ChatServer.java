import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.SocketException;
import java.util.Vector;

public class ChatServer {
    static Vector<DataOutputStream> connectedUsers = new Vector();
    
    public static void main(String[] args) throws IOException {
        ServerSocket ss;
        Socket s = null;
        
        ss = new ServerSocket(8080);
        System.out.println("Waiting for connections...");
        
        try {
            while(true) {
                s = ss.accept();
                System.out.println("Client connected.");
                
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                
                connectedUsers.add(dos);
                
                ClientHandler ch = new ClientHandler(s, dis, dos);
                ch.start();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    static class ClientHandler extends Thread {
        Socket s;
        DataInputStream dis;
        DataOutputStream dos;
        
        ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
        }
        
        @Override
        public void run() {
            DataOutputStream currentStream = null;
            while(true) {
                try {
                    String message = dis.readUTF();
                    for(DataOutputStream dos : connectedUsers) {
                        if(dos != this.dos)
                            dos.writeUTF(message);
                    }
                }
                catch(SocketException e) {
                    System.out.println("A user has disconnected.");
                    connectedUsers.remove(dos);
                    break;
                }
                catch(IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

