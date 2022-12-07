package control;

import persistence.dao.*;
import persistence.dto.*;
import protocol.*;
import service.MenuService;
import service.OptionService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class RequestController {
    private ResponseReceiver responseReceiver;
    private ResponseSender responseSender;
    private RequestSender requestSender;

    public RequestController() {
        responseReceiver = new ResponseReceiver();
        responseSender = new ResponseSender();
        requestSender = new RequestSender();
    }

    public void handleRequest(Header header, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        String userID_for_test = "store1"; // test용 유저아이디
        switch (header.code) {

            case Header.CODE_MENU_LIST:
                int store_id = inputStream.readInt();
                List<MenuDTO> menuList = new MyMenuDAO().selectStoreMenu(store_id);
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
                long order_price = inputStream.readLong();
                String order_num = inputStream.readUTF();
                new MyMenuDAO().updateMenuQuantity(updateMenu_id);
                new MyOrderDAO().updateOrderPrice(order_price, order_num);
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
                for (int i = 0; i < storeList2.size(); i++) {
                    if (storeList2.get(i).getUser_id().equals(userID_for_test)) {
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
                String order_num2 =inputStream.readUTF();
                List<OrderMenuDTO> omDTOS = moDAO.selectOrderMenuWithOrderNum(order_num2);

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
                break;

            case Header.CODE_UPDATE_USER_INFO:
                UserDTO user = UserDTO.read(inputStream);
                new MyUserDAO().userInfoUpdate(user);
                break;

            case Header.CODE_CANCEL_ORDER:
                int order_id = inputStream.readInt();
                new MyOrderDAO().updateOrderState_Cancle(order_id);
                break;

            case Header.CODE_INSERT_REVIEW:
                ReviewDTO reviewDTO = ReviewDTO.read(inputStream);
                new MyReviewDAO().insertReview(reviewDTO);
                break;
            case Header.CODE_REQUEST_RECEIVE_ACCEPT_USER_NUM:
                requestSender.sendAcceptUserNumReq(outputStream);
                break;
            case Header.CODE_REQUEST_RECEIVE_ACCEPT_STORE_NUM:
                requestSender.sendAcceptStoreNumReq(outputStream);
                break;


        }
    }
}