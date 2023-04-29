import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        Socket s = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        
        try {
            s = new Socket("localhost", 8080);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        Receiver receiver = new Receiver(s, dis, dos);
        Sender sender = new Sender(s, dis, dos);
        
        receiver.start();
        sender.start();
    }
}

class Sender extends Thread {
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    
    public Sender(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }
    
    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            while(true) {
                String message = sc.nextLine();
                dos.writeUTF(message);
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Receiver extends Thread {
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    
    public Receiver(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }
    
    @Override
    public void run() {
        try {
            while(true) {
                System.out.println(dis.readUTF());
            }
        }
        catch(IOException e) {
            System.out.println("Server shut down.");
            System.exit(0);
        }
    }
}
