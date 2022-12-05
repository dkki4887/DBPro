package control;

import control.*;
import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StartController {

    public static void handleStart(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        switch (header.code) {

            case Header.CODE_SIGN_UP:  // 가입 시작을 받음
                BodyMaker bodyMaker = new BodyMaker();
                byte[] resBody = bodyMaker.getBody();
                Header resHeader = new Header(
                        Header.TYPE_REQ,
                        Header.CODE_USER_DTO,
                        0);
                outputStream.write(resHeader.getBytes());
                outputStream.write(resBody);
                System.out.println("SIGN UP 시작 요청을 받음");
                break;

            case 0x02:
                //
                break;

        }
    }
}