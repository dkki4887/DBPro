
import control.TypeController;
import protocol.Header;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(3000);
        System.out.println("ServerSocket created.\nWaiting for connection ...\n\n");

        Socket socket = ss.accept();

        System.out.println("Client connected!\n");

        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        TypeController controller = new TypeController();

        boolean isContinue = true;
        String user_ID ;
        while(isContinue) {
            // read Header + Body
            Header header = Header.readHeader(is);
            byte[] body = new byte[header.length];
            is.read(body);
            DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
            user_ID= controller.handleType(header, bodyReader, os );
            if(user_ID.equals("false"))
                isContinue = false;
        } // end of while

    } // end of main
}
