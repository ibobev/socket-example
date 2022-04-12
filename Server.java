package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try{
            System.out.println("Waiting for client");
            ServerSocket serverSocket = new ServerSocket(1234);
            Socket socket = serverSocket.accept();
            System.out.println("Connection established!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readStr = in.readLine();
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println("Server says: " + readStr);
        } catch (Exception e){
            e.printStackTrace();
        }




    }
}
