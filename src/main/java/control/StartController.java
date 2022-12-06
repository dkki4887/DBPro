package control;

import control.*;
import persistence.dao.*;
import persistence.dto.*;
import protocol.*;
import service.UserService;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartController {
    private ResponseSender responseSender;
    private ResponseReceiver responseReceiver;
    private RequestSender requestSender;
    private RequestReceiver requestReceiver;

    public StartController()
    {
        this.responseSender = new ResponseSender();
        this.responseReceiver = new ResponseReceiver();
        this.requestSender = new RequestSender();
        this.requestReceiver = new RequestReceiver();
    }

    public void handleStart(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        String userID_for_test = "store1"; // test용 유저아이디

        switch (header.code) {

            case Header.CODE_SIGN_UP:  // 가입 시작을 받음
                requestSender.sendUserIDReq(outputStream);
                System.out.println("SIGN UP 시작 요청을 받음");
                break;

            case Header.CODE_LOG_IN:  // 가입 시작을 받음
                requestSender.sendUserIDReq(outputStream);
                System.out.println("SIGN UP 시작 요청을 받음");
                break;

            case Header.CODE_FOOD_ORDER: //주문 시작을 받음. 가게 정보 보내줌
                MyStoreDAO myStoreDAO = new MyStoreDAO();
                responseSender.sendStoreListAns(myStoreDAO.selectAllStoreNameAndId(), outputStream);
                break;


            case Header.CODE_ORDER_ACCEPT: //주문 승인or거절 시작을 받고 ,Order List 전송
                MyOrderDAO myOrderDAO = new MyOrderDAO();
                MyStoreDAO myStoreDAO2 = new MyStoreDAO();
                List<StoreDTO> storeList = myStoreDAO2.selectAll();
                int store_id = -1;
                for(int i = 0 ; i <storeList.size() ; i ++)
                {
                    if(storeList.get(i).getUser_id().equals( userID_for_test  ))
                    {
                        store_id = storeList.get(i).getStore_id();
                        break;
                    }
                }
                List<OrderDTO> orderList = myOrderDAO.selectOrder_store_Waiting(store_id);
                responseSender.sendOrderListAns(orderList, outputStream);
                break;

            case Header.CODE_USER_ACCEPT:
                MyUserDAO myUserDAO = new MyUserDAO();
                responseSender.sendWaitUserListAns(myUserDAO.selectUser_WaitingAccept(), outputStream);
                break;

            case Header.CODE_REVIEW_LOOKUP:
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
                List<Review_omDTO> reviewList = myReviewDAO.findReviewWithStoreAndNonReply(store_id2);

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

            case Header.CODE_INFO_AND_PW_FIX:
                responseSender.sendCheckPwdResult(bodyReader, outputStream);
                break;

            case Header.CODE_STORE_LOOKUP:
                myStoreDAO = new MyStoreDAO();
                responseSender.sendStoreAndReviewAns(myStoreDAO.selectStoreReview(), outputStream);
                break;

            case Header.CODE_INSERT_MENU:
                requestSender.sendMenuInfoReq(outputStream);
                break;

            case Header.CODE_UPDATE_STORE_TIME:
                requestSender.sendStoreTimeReq(outputStream);
                break;

            case Header.CODE_ORDER_LIST_LOOKUP:
                String user_id = bodyReader.readUTF();
                List<OrderDTO> orderDTOs = new MyOrderDAO().selectAllCustomerOrder(user_id);
                List<OrderMenuDTO> orderMenuDTOS = new MyOrderMenuDAO().selectAllMenu();
                List<OrderOptionDTO> orderOptionDTOS = new MyOrderMenuDAO().selectAllOption();

                responseSender.sendOrderList(orderDTOs, outputStream);
                responseSender.sendOrderMenuList(orderMenuDTOS, outputStream);
                responseSender.sendOrderOptionList(orderOptionDTOS, outputStream);
                responseSender.sendStoreListAns(new MyStoreDAO().selectAll(), outputStream);
                break;
        }
    }
}