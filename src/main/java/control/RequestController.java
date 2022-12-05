package control;

import control.*;
import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RequestController {

    public static void handleRequest(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        switch(header.code) {

            case Header.CODE_USER_ID: // 예시 ) 클라이언트 측에서 고객의 id를 요청했을 시
                int id = 0; //db에서 id를 불러오는 과정 생략
                BodyMaker bodyMaker = new BodyMaker();
                bodyMaker.addIntBytes(id);
                byte[] resBody = bodyMaker.getBody();
                Header resHeader = new Header(
                        Header.TYPE_RES,
                        Header.CODE_USER_ID,
                        resBody.length);
                outputStream.write(resHeader.getBytes());
                outputStream.write(resBody);
                break;

            case Header.TYPE_ANS:
                //
                break;

            case Header.TYPE_RES:
                //
                break;
        }
    }
}