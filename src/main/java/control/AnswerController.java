package control;

import persistence.dao.MyOrderDAO;
import persistence.dao.MyUserDAO;
import persistence.dto.OrderDTO;
import persistence.dto.UserDTO;
import protocol.*;
import service.UserService;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnswerController {


    public static String handleAnswer(Header header, DataInputStream bodyReader, DataOutputStream outputStream ) throws IOException {
        Scanner sc = new Scanner(System.in);
        ResponseSender responseSender = new ResponseSender();
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();
        RequestReceiver requestReceiver = new RequestReceiver();

        String USER_ID = "-";

        switch (header.code) {
            case Header.CODE_USER_ID:
                byte[] bodyy = new byte[header.length];
                bodyReader.read(bodyy);

                String user_id = bodyReader.readUTF();
                //String user_id = responseReceiver.receiveUserID(bodyReader);//user_id를 전송 받아 저장 후
                UserService userService = new UserService();

                if(!userService.idCheck(user_id))          //저장한 user_id를 체크함
                {
                    System.out.println("중복 안됨");
                    BodyMaker bodyMaker = new BodyMaker();
                    bodyMaker.addStringBytes(user_id);

                    byte[] id_resBody_unDup = bodyMaker.getBody();
                    Header id_resHeader_unDup = new Header(     //중복 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_SUCCESS,    //중복아닐 떈 success
                            id_resBody_unDup.length);

                    outputStream.write(id_resHeader_unDup.getBytes());
                    outputStream.write(id_resBody_unDup);

                }
                else {
                    System.out.println("중복됨");
                    BodyMaker bodyMaker = new BodyMaker();
                    bodyMaker.addStringBytes(user_id);
                    byte[] id_resBody_Dup = bodyMaker.getBody();

                    Header id_resHeader_Dup = new Header(         //실패 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_FAIL,   //중복일 땐 fail
                            id_resBody_Dup.length);

                    outputStream.write(id_resHeader_Dup.getBytes());
                    outputStream.write(id_resBody_Dup);
                }
                USER_ID = user_id;
                break;

            case Header.CODE_USER_DTO: // CODE_USER_INFO랑 같은 기능임
            case Header.CODE_USER_INFO:
                MyUserDAO myUserDAO = new MyUserDAO();
                UserDTO userDTO = responseReceiver.receiveUserInfo(bodyReader);//유저 정보 전송 받아 UserDTO에 저장함
                System.out.println(userDTO.getUser_id());
                myUserDAO.userAdd(userDTO);         //전송받은 UserDTO를 추가

                Header resHeader = new Header(            //성공 결과 전송
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        0);
                outputStream.write(resHeader.getBytes());
                byte[] body = new byte[header.length];
                outputStream.write(body);

                USER_ID = userDTO.getUser_id();
                break;

            case Header.CODE_USER_PW:
                List<String> user_IDAndPw = responseReceiver.receiveUserPW(bodyReader);//user_id랑 pw 정보를 받아 저장함
                UserService userService_use_pw = new UserService();

                if(userService_use_pw.pwCheck(user_IDAndPw.get(0), user_IDAndPw.get(1))) {  //id, pw이용해서 비번확인
                    BodyMaker bodyMaker = new BodyMaker();
                    bodyMaker.addStringBytes(user_IDAndPw.get(0));
                    byte[] pw_resBody_Success = bodyMaker.getBody();    //바디에는 유저 아이디 보내줌

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
                USER_ID = user_IDAndPw.get(0);
                break;


            case Header.CODE_FIXED_ORDER_DTO:
                MyOrderDAO myOrderDAO = new MyOrderDAO();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                int order_id = bodyReader.readInt();
                String user = bodyReader.readUTF();
                int store_id = bodyReader.readInt();
                long order_price = bodyReader.readLong();
                String order_state = bodyReader.readUTF() ;
                LocalDateTime order_orderTime= LocalDateTime.parse(bodyReader.readUTF(),formatter);
                String order_num = bodyReader.readUTF();

                OrderDTO orderDTO = new OrderDTO(order_id , user , store_id , order_price , order_state , order_orderTime , order_num);
                boolean isSuccess = true;
                if(orderDTO.getOrder_state().equals("배달중"))
                    myOrderDAO.updateOrderState_Delivery(orderDTO.getOrder_id());
                else if(orderDTO.getOrder_state().equals("취소"))
                    myOrderDAO.updateOrderState_Cancle(orderDTO.getOrder_id());
                else
                    isSuccess= false;

                if(isSuccess==true)
                {
                    System.out.println("주문 승인/거절 성공");
                    Header rs_header = new Header(
                            Header.TYPE_RES,
                            Header.CODE_SUCCESS,
                            0);
                    outputStream.write(rs_header.getBytes());
                }
                else
                {
                    System.out.println("주문 승인/거절 실패");
                    Header rs_header = new Header(
                            Header.TYPE_RES,
                            Header.CODE_FAIL,
                            0);
                    outputStream.write(rs_header.getBytes());
                }
                break;
        }
        return USER_ID;
    }
}
