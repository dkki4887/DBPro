package control;

import persistence.dao.MyUserDAO;
import persistence.dto.UserDTO;
import protocol.*;
import service.UserService;

import java.io.*;
import java.util.Scanner;

public class AnswerController {


    public static String handleAnswer(Header header, DataInputStream bodyReader, DataOutputStream outputStream ) throws IOException {
        Scanner sc = new Scanner(System.in);
        ResponseSender responseSender = new ResponseSender();
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();
        RequestReceiver requestReceiver = new RequestReceiver();

        String USER_ID = null;

        switch (header.code) {
            case Header.CODE_USER_ID:
                String user_id = responseReceiver.receiveUserID(bodyReader);//user_id를 전송 받아 저장 후
                UserService userService = new UserService();

                if(userService.idCheck(user_id))                            //저장한 user_id를 체크함
                    requestSender.sendUserInfoReq(user_id, outputStream);   //아이디 중복 없으면 유저 정보 요청
                else
                    requestSender.sendUserIDReq(outputStream);              //아이디 중복되면 user_id 다시 요청
                break;

            case Header.CODE_USER_DTO: // CODE_USER_INFO랑 같은 기능임
            case Header.CODE_USER_INFO:
                MyUserDAO myUserDAO = new MyUserDAO();
                UserDTO userDTO = responseReceiver.receiveUserInfo(bodyReader);//유저 정보 전송 받아 UserDTO에 저장함
                myUserDAO.userAdd(userDTO);                                     //전송받은 UserDTO를 추가

                Header resHeader = new Header(                                  //결과 전송
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        0);
                outputStream.write(resHeader.getBytes());
                byte[] body = new byte[header.length];
                outputStream.write(body);
                System.out.println("USER DTO를 받고 결과를 전송");
                break;

            case Header.CODE_USER_PW:
                //
                break;
        }

        return USER_ID;
    }
}
