package control;

import control.*;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyStoreDAO;
import persistence.dto.StoreDTO;
import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartController {

    public static void handleStart(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        ResponseSender responseSender = new ResponseSender();
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();
        RequestReceiver requestReceiver = new RequestReceiver();

        String userID_for_test = "test"; // test용 유저아이디

        switch (header.code) {

            case Header.CODE_SIGN_UP:  // 가입 시작을 받음
                requestSender.sendUserInfoReq(userID_for_test, outputStream);
                System.out.println("SIGN UP 시작 요청을 받음");
                break;

            case Header.CODE_FOOD_ORDER: //주문 시작을 받음. 가게 정보 보내줌
                System.out.println("시작 요청을 받음!!");
                MyStoreDAO myStoreDAO = new MyStoreDAO();
                responseSender.sendStoreListAns(myStoreDAO.selectAllStoreNameAndId(), outputStream);
                System.out.println("가게 리스트 보내줌!!!");
                break;


            case Header.CODE_ORDER_ACCEPT: //주문 승인or거절 시작을 받고 ,Order List 전송
                MyOrderDAO myOrderDAO = new MyOrderDAO();
                MyStoreDAO myStoreDAO2 = new MyStoreDAO();
                List<StoreDTO> storeList = new ArrayList<StoreDTO>();
                storeList = myStoreDAO2.selectAll();
                int store_id = -1;
                for(int i = 0 ; i <storeList.size() ; i ++)
                {
                    if(storeList.get(i).getUser_id().equals( userID_for_test  ))
                        store_id = storeList.get(i).getStore_id();
                }
                responseSender.sendOrderListAns(myOrderDAO.selectOrder_store_Waiting(store_id) , outputStream);
                break;


        }
    }
}