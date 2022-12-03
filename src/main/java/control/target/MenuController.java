package control.target;

import protocol.BodyMaker;
import protocol.Header;
import protocol.MySerializableClass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class MenuController {

//    public void handleRead(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
//
//        switch(header.option) {
//
//            case Header.OPTION_PLAYER_ALL:
//                findAll(outputStream);
//                break;
//
//            case Header.OPTION_PLAYER_BY_ID:
//                int id = bodyReader.readInt();
//                findById(id, outputStream);
//                break;
//
//            case Header.OPTION_PLAYER_BY_NAME:
//                String name = bodyReader.readUTF();
//                findByName(name, outputStream);
//                break;
//
//        }
//    }
//
//
//    public void findAll(DataOutputStream outputStream) throws IOException {
//        List<MySerializableClass> list = Database.getAllPlayer();
//
//        BodyMaker bodyMaker = new BodyMaker();
//        bodyMaker.add(list);
//
//        byte[] resBody = bodyMaker.getBody();
//
//        Header resHeader = new Header(
//                Header.TYPE_RES,
//                Header.ACTION_READ,
//                Header.TARGET_PLAYER,
//                Header.OPTION_PLAYER_ALL,
//                Header.STATUS_SUCCESS,
//                resBody.length
//        );
//
//        outputStream.write(resHeader.getBytes());
//        outputStream.write(resBody);
//
//    }


}
