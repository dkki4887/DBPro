package control;

import persistence.dao.MyMenuDAO;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyReviewDAO;
import persistence.dao.MyStoreDAO;
import persistence.dto.*;
import protocol.BodyMaker;
import protocol.Header;
import protocol.ResponseReceiver;
import protocol.ResponseSender;
import service.MenuService;
import service.OptionService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class RequestController {

    public static void handleRequest(Header header, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        ResponseReceiver responseReceiver = new ResponseReceiver();
        ResponseSender responseSender = new ResponseSender();
        String userID_for_test = "store1"; // test용 유저아이디
        switch(header.code) {

            case Header.CODE_MENU_LIST:
                int store_id = responseReceiver.receiveStoreID(inputStream);
                List<MenuDTO> menuList = new MenuService().selectStoreMenu(store_id);
                responseSender.sendMenuListAns(menuList, outputStream);
                break;

            case Header.CODE_OPTION_LIST:
                int menu_id = responseReceiver.receiveMenuID(inputStream);
                List<OptionDTO> optionList = new OptionService().selectMenuOption(menu_id);
                responseSender.sendOptionListAns(optionList, outputStream);
                break;

            case Header.CODE_INSERT_ORDER:
                OrderDTO order = responseReceiver.receiveOrder(inputStream);
                MyOrderDAO myOrderDAO = new MyOrderDAO();
                myOrderDAO.insertOrder(order);
                break;

            case Header.CODE_UPDATE_MENU_QUANTITY:
                int updateMenu_id = responseReceiver.receiveMenuID(inputStream);
                MyMenuDAO myMenuDAO = new MyMenuDAO();
                myMenuDAO.updateMenuQuantity(updateMenu_id);
                break;

            case Header.CODE_REVIEW_REPLY:
                MyReviewDAO myReviewDAO = new MyReviewDAO();
                MyStoreDAO myStoreDAO3 = new MyStoreDAO();
                List<StoreDTO> storeList2 = myStoreDAO3.selectAll();
                int store_id2 = -1;
                for(int i = 0 ; i <storeList2.size() ; i ++)
                {
                    if(storeList2.get(i).getUser_id().equals( userID_for_test  ))
                    {
                        store_id2 = storeList2.get(i).getStore_id();
                        break;
                    }
                }
                System.out.println(store_id2);
                List<Review_omDTO> reviewList = myReviewDAO.findReviewWithStoreAndReply(store_id2);

                BodyMaker bodyMaker = new BodyMaker();
                bodyMaker.addIntBytes(reviewList.size());
                for(int i = 0 ; i <reviewList.size(); i ++)
                    bodyMaker.add(reviewList.get(i));
                byte[] review_body = bodyMaker.getBody();

                System.out.println("보내나?");

                Header review_header = new Header(
                        Header.TYPE_ANS,
                        Header.CODE_REVIEW_LOOKUP,
                        review_body.length);
                outputStream.write(review_header.getBytes());
                outputStream.write(review_body);
                break;
        }
    }
}