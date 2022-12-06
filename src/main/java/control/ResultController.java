package control;

import control.*;
import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ResultController {

    public void handleResult(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        switch (header.code) {

            case 0x01:  // 성공
                //
                break;

            case 0x02: // 실패
                //
                break;

        }
    }
}