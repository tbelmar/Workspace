import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

class HTTPServer {
    private static Vector<GuessClass> guessClasses = new Vector();
    
    private class Cookie {
        String name;
        String value;
        
        Cookie(String name, String value) {
            this.name = name;
            this.value = value;
        }
        
        Cookie(String[] c) {
            this.name = c[0];
            this.value = c[1];
        }
        
        @Override
        public String toString() {
            return name + "=" + value + ";";
        }
    }
    
    private class GuessClass {
        private String clientId;
        private int nOfGuesses = 0;
        private int correctGuess;
        private boolean gameFinished = false;
        
        GuessClass(String clientId) {
            this.clientId = clientId;
            correctGuess = (int)(Math.random() * 100) + 1;
        }
        
        public String getClientId() {
            return clientId;
        }
        
        public int getNOfGuesses() {
            return nOfGuesses;
        }
        
        public boolean hasFinished() {
            return gameFinished;
        }
        
        public int guess(int guess) {
            nOfGuesses++;
            if(guess > correctGuess) return -1;
            if(guess < correctGuess) return 1;
            gameFinished = true;
            return 0;
        }
    }
    
    private void serve(int port) {
        int clients = 0;
        
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Listening for connection on port " + port + "...");
            
            while(true) {
                /*
                CLIENT REQUEST
                CLIENT REQUEST
                CLIENT REQUEST
                */
                Socket s = ss.accept();
                BufferedReader request = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String requestLine = request.readLine();
                if(requestLine == null) continue;
                System.out.println(requestLine);
                
                StringTokenizer tokens = new StringTokenizer(requestLine, " ?");
                tokens.nextToken();
                String requestedDocument = tokens.nextToken();
                System.out.println("Loading page: " + requestedDocument);
                
                // Retrieve cookies from request
                Cookie[] cookies = {};
                
                while( !(requestLine = request.readLine()).isEmpty()) {
                    cookies = cookies.length == 0 ? getCookies(requestLine) : cookies;
                    
                    System.out.println(requestLine);
                }
                System.out.println();
                
                s.shutdownInput();
                
                /*
                SERVER RESPONSE
                SERVER RESPONSE
                SERVER RESPONSE
                */
                PrintStream response = new PrintStream(s.getOutputStream());
                response.println("HTTP/1.1 200 OK");
                response.println("Server: Trash 0.1 Beta");
                if(requestedDocument.indexOf(".html") != -1)
                    response.println("Content-Type: text/html");
                if(requestedDocument.indexOf(".gif") != -1)
                    response.println("Content-Type: image/gif");
                
                String clientId = getValueFromCookies("clientId", cookies);
                GuessClass guessClass = findGuessClass(clientId);
                
                // Check if guess is submitted
                String param = tokens.nextToken();
                int cmp = 0;
                if(param.startsWith("guess=") && !param.equals("guess=") && guessClass != null && !guessClass.hasFinished())
                    cmp = guessClass.guess(Integer.parseInt(param.substring("guess=".length())));
                if(param.startsWith("restart="))
                    guessClass = null;
                    
                // If client is new
                if(clientId == null || guessClass == null) {
                    response.println("Set-Cookie: clientId=" + ++clients + "; expires-Wednesday,31-Dec-21 21:00:00 GMT");
                    requestedDocument = "/start.html";
                    guessClasses.add(new GuessClass("" + clients));
                }
                // If game isn't done yet
                else if(!guessClass.hasFinished()) {
                    requestedDocument = "/index.html";
                }
                // If client won the game
                else {
                    requestedDocument = "/end.html";
                }
                
                response.println();
                
                File f = new File("." + requestedDocument);
                
                FileInputStream infil = new FileInputStream(f);
                byte[] b = new byte[1024];
                
                    
                while(infil.available() > 0) {
                    if(requestedDocument.equals("/end.html"))
                        response.println("<p>Congratulations! You beat the game in " + guessClass.getNOfGuesses() + " tries.</p>");
                    else if(requestedDocument.equals("/index.html")) {
                        if(cmp < 0)
                            response.println("<p>Try guessing lower. You have guessed " + guessClass.getNOfGuesses() + " times.</p>");
                        else if(cmp > 0)
                            response.println("<p>Try guessing higher. You have guessed " + guessClass.getNOfGuesses() + " times.</p>");
                    }
                    response.write(b, 0, infil.read(b));
                }
                
                
                
                s.shutdownOutput();
                s.close();   
                
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
    
    private static GuessClass findGuessClass(String clientId) {
        for(GuessClass gc : guessClasses)
            if(gc.getClientId().equals(clientId))
                return gc;
        return null;
    }
    
    // Searches Cookies array for a certain name and returns its value
    private static String getValueFromCookies(String name, Cookie[] cookies) {
        for(Cookie c: cookies)
            if(c.name.equals(name))
                return c.value;
        return null;
    }
    
    
    
    // Formats HTTP Cookies header into an array of Cookies
    public Cookie[] getCookies(String requestLine) {
        int totalCookies = 0;
        Cookie[] cookies = {};
        
        if(requestLine.indexOf("Cookie: ") == 0) {
            String[] cookiesStr = requestLine.substring("Cookie: ".length()).split("; ");
            
            for(String c:cookiesStr) totalCookies++;
            
            cookies = new Cookie[totalCookies];
            
            for(int i = 0; i < cookies.length; i++)
                cookies[i] = new Cookie(cookiesStr[i].split("="));
        }
        
        return cookies;
    }
    
    public static void main(String[] args) {
        HTTPServer server = new HTTPServer();
        server.serve(80);
    }
}