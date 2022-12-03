package control;

import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AnswerController {

    public static void handleAnswer(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch (header.code) {

            case Header.CODE_CUSTOMER_ID:
                //id 받았으니 pw request 보내기
                break;

            case Header.CODE_CUSTOMER_PW:
                //pw 받았으니 db에서 검색 후 로그인 result 보내기
                break;
        }
    }
}
