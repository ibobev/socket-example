package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try{
            System.out.println("Client started");

            Socket socket = new Socket("localhost", 1234);

            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter your input: ");
            String inStr = clientInput.readLine();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(inStr);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(in.readLine());


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
