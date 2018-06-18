package glory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private final Socket client;
    ServerSocket serverSocket = null;

    public RequestHandler(Socket client) {

        this.client = client;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            System.out.println("Thread started with name:" + Thread.currentThread().getName());
            hello(Thread.currentThread().getName());
        } catch (IOException e) {
            System.out.println("I/O exception " + e);
        } catch (Exception ex) {
            System.out.println("Exception is thread run. Exception: " + ex);
        }
    }

    public static void hello(String threadName) {
        System.out.println("Hello");
        String retval[] = threadName.split("pool-1-thread-");
        int threadNumber = Integer.parseInt(retval[1]);
        if (threadNumber >= 9) {
            System.out.println("Maximum number of players are connected to the game. Please retry later.");
        }
    }
}
