package control;

import control.*;
import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StartController {

    public static void handleStart(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        ResponseSender responseSender = new ResponseSender();
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();
        RequestReceiver requestReceiver = new RequestReceiver();

        switch (header.code) {

            case Header.CODE_SIGN_UP:  // 가입 시작을 받음
                requestSender.sendUserInfoReq(outputStream);
                System.out.println("SIGN UP 시작 요청을 받음");
                break;

            case 0x02:
                //
                break;

        }
    }
}