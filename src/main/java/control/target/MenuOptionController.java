package control.target;

import protocol.BodyMaker;
import protocol.Header;
import protocol.MySerializableClass;

public class MenuOptionController {

//    public void handleRead(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException{
//
//        switch(header.option) {
//
//            case Header.FIND_TEAM_ALL:
//                findAll(outputStream);
//                break;
//        }
//
//    }
//
//    public void findAll(DataOutputStream outputStream) throws IOException {
//
//        List<MySerializableClass> list = Database.getALlTeam();
//
//        BodyMaker bodyMaker = new BodyMaker();
//        bodyMaker.add(list);
//
//        byte[] resBody = bodyMaker.getBody();
//
//        Header resHeader = new Header(
//                Header.TYPE_RES,
//                Header.ACTION_READ,
//                Header.TARGET_TEAM,
//                Header.FIND_TEAM_ALL,
//                Header.STATUS_SUCCESS,
//                resBody.length
//        );
//
//        outputStream.write(resHeader.getBytes());
//        outputStream.write(resBody);
//
//    }


}
