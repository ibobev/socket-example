package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Server(int port) {
        initServer(port);
    }

    public void initServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for client.");
        } catch (IOException e) {
            throw new RuntimeException("Server init failed", e);
        }
    }

    public void communicateWithClient() throws IOException {
        try {
            boolean isNotExit = true;
            socket = serverSocket.accept();
            System.out.println("Client connected");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // read input from client
            out = new PrintWriter(socket.getOutputStream(), true); // send output to client
            while (isNotExit) {
                String readStr = in.readLine(); // readStr -> reads the input sent from the client withe the InputStreamReader
                if ("exit".equals(readStr)) {
                    isNotExit = false;
                } else {
                    out.println(readStr + " echo"); // sends back output to the client with the PrintWriter socket-getOutputStream
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeServer();
        }
    }

    public void closeServer() throws IOException {
        serverSocket.close();
        socket.close();
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(1234);
        server.communicateWithClient();
    }
}
