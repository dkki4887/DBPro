import control.TypeController;
import protocol.Header;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ClientHandler extends Thread
{
    private Socket commSocket;
    private int cliID;

    ClientHandler(Socket commSocket, int cliID){
        this.commSocket = commSocket;
        this.cliID = cliID;
    }

    public void run(){

        try{
            DataInputStream is = new DataInputStream(commSocket.getInputStream());
            DataOutputStream os = new DataOutputStream(commSocket.getOutputStream());

            TypeController controller = new TypeController();

            boolean isContinue = true;
            String user_ID ;
            while(isContinue) {
                // read Header + Body
                Header header = Header.readHeader(is);
                byte[] body = new byte[header.length];
                is.read(body);
                DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
                String idStr = controller.handleType(header, bodyReader, os );
                if(idStr != "-")
                    user_ID = idStr;
                if(idStr.equals("0"))
                    isContinue = false;
            }
            }
        catch (IOException ex) {
            System.err.println(ex);
        }

        System.out.println("Client #" + cliID + ": " + "Socket closing");


    }
}