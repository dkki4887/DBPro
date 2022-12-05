package control;

import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RequestController {

    public static void handleRequest(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        switch(header.code) {

            case Header.TYPE_ANS:
                //
                break;

            case Header.TYPE_RES:
                //
                break;
        }
    }
}