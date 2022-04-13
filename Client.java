package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader clientInput;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String host, int port) {
        initClient(host, port);
    }

    public void initClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("Client started");
        } catch (IOException e) {
            throw new RuntimeException("Client init failed", e);
        }
    }

    public void communicateWithServer() throws IOException {
        try {
            System.out.println("Client connected to server");
            boolean isNotExit = true;
            clientInput = new BufferedReader(new InputStreamReader(System.in)); // take client input
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // read input from server
            out = new PrintWriter(socket.getOutputStream(), true); // send output to server


            while (isNotExit) {
                System.out.print(">:");
                String inStr = clientInput.readLine(); // store client input
                out.println(inStr); // send message (inStr) to server
                if ("exit".equals(inStr)) {
                    isNotExit = false;
                } else {
                    System.out.println(in.readLine()); // print server response message
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeClient();
        }
    }

    public void closeClient() throws IOException {
        socket.close();
        clientInput.close();
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 1234);
        client.communicateWithServer();
    }
}
