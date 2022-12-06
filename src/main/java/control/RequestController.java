package control;

import persistence.dao.*;
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
   private ResponseReceiver responseReceiver;
   private ResponseSender responseSender;

   public RequestController()
   {
       responseReceiver = new ResponseReceiver();
       responseSender = new ResponseSender();
   }

    public void handleRequest(Header header, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        String userID_for_test = "store1"; // test용 유저아이디
        switch(header.code) {

            case Header.CODE_MENU_LIST:
                int store_id = inputStream.readInt();
                List<MenuDTO> menuList = new MenuService().selectStoreMenu(store_id);
                responseSender.sendMenuListAns(menuList, outputStream);
                System.out.println("메뉴리스트 보내줌!!");
                break;

            case Header.CODE_OPTION_LIST:
                int menu_id = inputStream.readInt();
                List<OptionDTO> optionList = new OptionService().selectMenuOption(menu_id);
                responseSender.sendOptionListAns(optionList, outputStream);
                System.out.println("옵션리스트 보내줌!!");
                break;

            case Header.CODE_INSERT_ORDER:
                OrderDTO order = new OrderDTO().read(inputStream);
                MyOrderDAO myOrderDAO = new MyOrderDAO();
                myOrderDAO.insertOrder(order);
                System.out.println("주문 넣음!!");
                break;

            case Header.CODE_UPDATE_MENU_QUANTITY:
                int updateMenu_id = inputStream.readInt();
                MyMenuDAO myMenuDAO = new MyMenuDAO();
                myMenuDAO.updateMenuQuantity(updateMenu_id);
                System.out.println("메뉴 수량 바꿈!!");
                break;

            case Header.CODE_INSERT_ORDER_MENU:
                OrderMenuDTO orderMenuDTO = new OrderMenuDTO().read(inputStream);
                MyOrderMenuDAO myOrderMenuDAO = new MyOrderMenuDAO();
                myOrderMenuDAO.insertOrderMenu(orderMenuDTO);
                System.out.println("오더 메뉴 넣음!!");
                break;

            case Header.CODE_INSERT_ORDER_OPTION:
                OrderOptionDTO orderOptionDTO = new OrderOptionDTO().read(inputStream);
                myOrderMenuDAO = new MyOrderMenuDAO();
                myOrderMenuDAO.insertOrderOption(orderOptionDTO);
                System.out.println("오더 옵션 넣음!!");
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
                List<Review_omDTO> reviewList = myReviewDAO.findReviewWithStoreAndReply(store_id2);

                BodyMaker bodyMaker = new BodyMaker();
                bodyMaker.addIntBytes(reviewList.size());
                for(int i = 0 ; i <reviewList.size(); i ++)
                    bodyMaker.add(reviewList.get(i));
                byte[] review_body = bodyMaker.getBody();
                Header review_header = new Header(
                        Header.TYPE_ANS,
                        Header.CODE_REVIEW_LOOKUP,
                        review_body.length);
                outputStream.write(review_header.getBytes());
                outputStream.write(review_body);
                break;

            case Header.CODE_UPDATE_USER_INFO:
                UserDTO user = UserDTO.read(inputStream);
                new MyUserDAO().userInfoUpdate(user);
                break;
        }
    }
}