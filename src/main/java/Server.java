import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static final int PORT = 3000;

    public static void main(String args[]) throws IOException{
        ServerSocket listenSocket = null;
        Socket commSocket = null;
        int cnt = 0;

        try{
            listenSocket = new ServerSocket(PORT);
            System.out.println("Waiting for connection...");

            while(true){
                commSocket = listenSocket.accept();
                cnt++;

                System.out.println("Connection received from " + commSocket.getInetAddress().getHostName() + " : " + commSocket.getPort());

                ClientHandler cliHandler = new ClientHandler(commSocket, cnt);
                cliHandler.start();
            }
        }catch(IOException e){
            System.err.println(e);
        } finally{
            if(listenSocket != null){
                try{
                    listenSocket.close();
                }catch(IOException e){
                    System.out.println(e);
                }
            }
        }
    }
}