package protocol;

import persistence.dto.*;
import service.UserService;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class ResponseReceiver {

    public OrderDTO receiveOrder(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        OrderDTO order = new OrderDTO();
        order.read(bodyReader);
        return order;
    }

    public String receiveUserID(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String user_id = bodyReader.readUTF();
        return user_id;
    }

    public void receiveUserPW(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String user_pw = bodyReader.readUTF();
    }

    public void receiveUserName(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String user_name = bodyReader.readUTF();
    }

    public void receiveUserAddress(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String user_address = bodyReader.readUTF();
    }

    public void receiveUserPhone(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String user_phone = bodyReader.readUTF();
    }

    public void receiveUserType(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        int user_type = bodyReader.readInt();
    }

    public UserDTO receiveUserInfo(DataInputStream inputStream) throws IOException{
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        UserDTO userInfo = new UserDTO();
        userInfo.read(bodyReader);

        return userInfo;
    }

    public int receiveStoreID(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        return bodyReader.readInt();
    }

    public void receiveStoreName(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String store_name = bodyReader.readUTF();
    }

    public void receiveStoreAddress(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String store_address = bodyReader.readUTF();
    }

    public void receiveStorePhone(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String store_phone = bodyReader.readUTF();
    }

    public void receiveStoreCategory(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String store_category = bodyReader.readUTF(); //string 아니면 바꿔야함
    }

    public void receiveStoreState(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean store_state = bodyReader.readBoolean();
    }

    public void receiveStoreOpen(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String store_open = bodyReader.readUTF();
    }

    public void receiveStoreClose(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String store_close = bodyReader.readUTF();
    }

    public void receiveStoreIntroduce(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String store_introduce = bodyReader.readUTF();
    }

    public void receiveStoreCountOfSale(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        int store_count_of_sale = bodyReader.readInt();
    }

    public void receiveStoreTotalSales(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        long store_phone = bodyReader.readLong();
    }

    public void receiveStoreTime(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String store_phone = bodyReader.readUTF();
    }

    public int receiveMenuID(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        return bodyReader.readInt();
    }

    public void receiveMenuName(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String menu_name = bodyReader.readUTF();
    }

    public void receiveMenuPrice(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        long store_phone = bodyReader.readLong();
    }

    public void receiveMenuStock(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        int menu_stock = bodyReader.readInt();
    }

    public void receiveMenuCategory(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String menu_category = bodyReader.readUTF();
    }

    public void receiveMenuList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<MenuDTO> menuDTOs = null;
        int size = bodyReader.readInt();

        for(int i=0; i<size; i++) {
            menuDTOs.add(MenuDTO.readMenu(bodyReader));
        }
    }

    public void receiveOptionID(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        int option_id = bodyReader.readInt();
    }

    public void receiveOptionPrice(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        long option_id = bodyReader.readLong();
    }

    public void receiveOptionList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<OptionDTO> optionDTOs = null;
        int size = bodyReader.readInt();

        for(int i=0; i<size; i++) {
            optionDTOs.add(OptionDTO.readOption(bodyReader));
        }
    }

    public void receiveOrderID(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        int order_id = bodyReader.readInt();
    }

    public void receiveOrderPrice(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        long order_price = bodyReader.readLong();
    }

    public void receiveOrderState(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String order_id = bodyReader.readUTF();
    }

    public void receiveOrderList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<OrderDTO> orderDTOs = null;
        int size = bodyReader.readInt();

        for(int i=0; i<size; i++) {
            orderDTOs.add(OrderDTO.readOrder(bodyReader));
        }
    }

    public void receiveOrderMenuList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<OrderMenuDTO> orderMenuDTOs = null;
        int size = bodyReader.readInt();

        for(int i=0; i<size; i++) {
            orderMenuDTOs.add(OrderMenuDTO.readOrderMenu(bodyReader));
        }
    }

    public void receiveReviewID(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        int review_id = bodyReader.readInt();
    }

    public void receiveReviewContent(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        String review_content = bodyReader.readUTF();
    }



//    public void receiveOnePlayer(DataInputStream inputStream) throws IOException {
//
//        Header header = Header.readHeader(inputStream);
//        byte[] body = new byte[header.bodySize];
//        inputStream.read(body);
//        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
//
//        Player player = Player.readPlayer(bodyReader);
//        System.out.println(player);
//
//    }
//
//    public void receivePlayerList(DataInputStream inputStream) throws IOException {
//
//        Header header = Header.readHeader(inputStream);
//        byte[] body = new byte[header.bodySize];
//        inputStream.read(body);
//        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
//
//        int size = bodyReader.readInt();
//
//        for(int i=0; i<size; i++) {
//            Player player = Player.readPlayer(bodyReader);
//            System.out.println(player);
//        }
//
//    }
//
//    public void receiveTeamList(DataInputStream inputStream) throws IOException {
//
//        Header header = Header.readHeader(inputStream);
//        byte[] body = new byte[header.bodySize];
//        inputStream.read(body);
//        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
//
//        int size = bodyReader.readInt();
//
//        for(int i=0; i<size; i++) {
//            Team team = Team.readTeam(bodyReader);
//            System.out.println(team);
//        }
//
//    }


}
