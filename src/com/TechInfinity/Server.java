package glory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    public void Server(String args[]) throws IOException{
        System.out.println("Start of main");
        if (args.length<1){
            System.out.println("Usage: java echoserver 8000");
            System.exit(1);
        }
        int portNumber = 8000;
        int i=1;
        ExecutorService executor = null;
        try (ServerSocket serverSocket = new ServerSocket(portNumber);){
            executor = Executors.newFixedThreadPool(20);
            System.out.println("Waiting for clients");
            while (true){
                if (i>=9){
                    Socket clientSocket2 = serverSocket.accept();
                    Runnable worker = new glory.RequestHandler(clientSocket2);
                    executor.execute(worker);
                }
                else{
                    Socket clientSocket1 = serverSocket.accept();
                    Runnable worker = new glory.RequestHandler(clientSocket1);
                    executor.execute(worker);
                }
                i++;
            }
        }
        catch(IOException e){
            System.out.println("Exception caught when trying to listen on port" + portNumber + "or listening for a connection");
            System.out.println(e.getMessage());
        }
        finally {
            if (executor != null){
                executor.shutdown();
            }
        }
    }
}


