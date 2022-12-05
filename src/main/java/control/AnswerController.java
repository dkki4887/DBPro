package control;

import persistence.dao.MyUserDAO;
import persistence.dto.UserDTO;
import protocol.BodyMaker;
import protocol.Header;

import java.io.*;

public class AnswerController {

    public static void handleAnswer(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch (header.code) {

            case Header.CODE_CUSTOMER_ID:
/*                id = bodyReader.readUTF();
                Header resHeader = new Header(
                        Header.TYPE_REQ,
                        Header.CODE_CUSTOMER_PW,
                        0);
                outputStream.write(resHeader.getBytes());*/
                break;

            case Header.CODE_USER_DTO:
                MyUserDAO myUserDAO = new MyUserDAO();
                String user_id = bodyReader.readUTF();
                String user_pw = bodyReader.readUTF();
                String user_name = bodyReader.readUTF();
                String user_address  = bodyReader.readUTF();
                String user_phone = bodyReader.readUTF();
                int user_category =bodyReader.readInt();
                UserDTO userDTO = new UserDTO(user_id , user_pw, user_name , user_address , user_phone , user_category);
                myUserDAO.userAdd(userDTO);

                Header resHeader = new Header(
                        Header.TYPE_RES,
                        (byte) 0x01,
                        0);
                outputStream.write(resHeader.getBytes());
                byte[] body = new byte[header.length];
                outputStream.write(body);
                System.out.println("USER DTO를 받고 결과를 전송");
                break;

            case Header.CODE_CUSTOMER_PW:
                //pw 받았으니 db에서 검색 후 로그인 result 보내기
                break;
        }
    }
}
