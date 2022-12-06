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

                List<Review_omDTO> replyList = myReviewDAO.findReviewWithStoreAndReply(store_id2);

                BodyMaker bodyMaker = new BodyMaker();
                bodyMaker.addIntBytes(replyList.size());
                for(int i = 0 ; i <replyList.size(); i ++)
                    bodyMaker.add(replyList.get(i));
                byte[] reply_body = bodyMaker.getBody();


                Header reply_header = new Header(
                        Header.TYPE_RES,
                        Header.CODE_REVIEW_REPLY,
                        reply_body.length);
                outputStream.write(reply_header.getBytes());
                outputStream.write(reply_body);
                break;

            case Header.CODE_ORDERED_MENU_LIST:
                MyOrderDAO moDAO = new MyOrderDAO();
                String order_num =inputStream.readUTF();
                List<OrderMenuDTO> omDTOS = moDAO.selectOrderMenuWithOrderNum(order_num);

                BodyMaker omBM = new BodyMaker();
                omBM.addIntBytes(omDTOS.size());
                for(int i = 0 ; i < omDTOS.size() ; i ++)
                    omBM.add(omDTOS.get(i));
                byte[] omBody = omBM.getBody();
                Header omHeader = new Header(
                        Header.TYPE_ANS,
                        Header.CODE_ORDERED_MENU_LIST,
                        omBody.length);
                outputStream.write(omHeader.getBytes());
                outputStream.write(omBody);
                break;


            case Header.CODE_ORDERED_OPTION:
                MyOrderDAO ooDAO = new MyOrderDAO();
                String orderMenu_id =inputStream.readUTF();
                List<OrderOptionDTO> ooDTOS = ooDAO.selectOrderOptionWithOrderMenuID(orderMenu_id);

                BodyMaker ooBM = new BodyMaker();
                ooBM.addIntBytes(ooDTOS.size());
                for(int i = 0 ; i < ooDTOS.size() ; i ++)
                    ooBM.add(ooDTOS.get(i));
                byte[] ooBody = ooBM.getBody();
                Header ooHeader = new Header(
                        Header.TYPE_ANS,
                        Header.CODE_ORDERED_OPTION,
                        ooBody.length);
                outputStream.write(ooHeader.getBytes());
                outputStream.write(ooBody);
        }
    }
}