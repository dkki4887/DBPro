package control;

import persistence.dto.UserDTO;
import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AnswerController {

    public static void handleAnswer(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch (header.code) {

            case Header.CODE_USER_ID:
                id = bodyReader.readUTF();
                Header resHeader = new Header(
                        Header.TYPE_REQ,
                        Header.CODE_CUSTOMER_PW,
                        0);
                outputStream.write(resHeader.getBytes());
                break;

            case Header.CODE_USER_DTO:
                UserDTO u = bodyReader.;
                Header resHeader = new Header(
                        Header.TYPE_REQ,
                        Header.CODE_CUSTOMER_PW,
                        0);
                outputStream.write(resHeader.getBytes());
                Header.readHeader(inputStream);
                byte[] body = new byte[header.length];
                inputStream.read(body);
                DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        Player player = Player.readPlayer(bodyReader);
        System.out.println(player);
                break;

            case Header.CODE_USER_PW:
                //pw 받았으니 db에서 검색 후 로그인 result 보내기
                break;
        }
    }
}
