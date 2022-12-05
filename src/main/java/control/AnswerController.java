package control;

import persistence.dao.MyUserDAO;
import persistence.dto.UserDTO;
import protocol.*;
import service.UserService;

import java.io.*;
import java.util.List;
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

                if(!userService.idCheck(user_id))          //저장한 user_id를 체크함
                {
                    BodyMaker bodyMaker = new BodyMaker();
                    bodyMaker.addStringBytes(user_id);

                    byte[] resBody = bodyMaker.getBody();
                    Header id_resHeader_Success = new Header(     //성공 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_SUCCESS,
                            resBody.length);
                    outputStream.write(id_resHeader_Success.getBytes());
                    outputStream.write(resBody);

                }
                else {
                    byte[] id_resBody_Fail = new byte[header.length];
                    Header id_resHeader_Fail = new Header(         //실패 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_FAIL,
                            0);
                    outputStream.write(id_resHeader_Fail.getBytes());
                    outputStream.write(id_resBody_Fail);
                }
                break;

            case Header.CODE_USER_DTO: // CODE_USER_INFO랑 같은 기능임
            case Header.CODE_USER_INFO:
                MyUserDAO myUserDAO = new MyUserDAO();
                UserDTO userDTO = responseReceiver.receiveUserInfo(bodyReader);//유저 정보 전송 받아 UserDTO에 저장함
                myUserDAO.userAdd(userDTO);         //전송받은 UserDTO를 추가

                Header resHeader = new Header(            //성공 결과 전송
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        0);
                outputStream.write(resHeader.getBytes());
                byte[] body = new byte[header.length];
                outputStream.write(body);
                break;

            case Header.CODE_USER_PW:
                List<String> user_IDAndPw = responseReceiver.receiveUserPW(bodyReader);//user_id랑 pw 정보를 받아 저장함
                UserService userService_use_pw = new UserService();

                if(userService_use_pw.pwCheck(user_IDAndPw.get(0), user_IDAndPw.get(1))) {  //id, pw이용해서 비번확인
                    BodyMaker bodyMaker = new BodyMaker();
                    bodyMaker.addStringBytes(user_IDAndPw.get(0));
                    byte[] pw_resBody_Success = bodyMaker.getBody();

                    Header pw_resHeader = new Header(       //성공결과 전송
                            Header.TYPE_RES,
                            Header.CODE_SUCCESS,
                            pw_resBody_Success.length);
                    outputStream.write(pw_resHeader.getBytes());
                    outputStream.write(pw_resBody_Success);
                }
                else
                {
                    BodyMaker bodyMaker = new BodyMaker();
                    bodyMaker.addStringBytes(user_IDAndPw.get(0));
                    byte[] pw_resBody_Fail = bodyMaker.getBody();

                    Header pw_resHeader = new Header(        //실패 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_FAIL,
                            pw_resBody_Fail.length);
                    outputStream.write(pw_resHeader.getBytes());
                    outputStream.write(pw_resBody_Fail);
                }
                break;
        }

        return USER_ID;
    }
}
