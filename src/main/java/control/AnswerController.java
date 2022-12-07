package control;

import persistence.dao.*;
import persistence.dto.*;
import protocol.*;
import service.MenuService;
import service.StoreService;
import service.UserService;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnswerController {
    private Scanner sc;
    private ResponseSender responseSender;
    private ResponseReceiver responseReceiver;
    private RequestSender requestSender;
    private RequestReceiver requestReceiver;

    public AnswerController() {
        this.sc = new Scanner(System.in);
        this.responseSender = new ResponseSender();
        this.responseReceiver = new ResponseReceiver();
        this.requestSender = new RequestSender();
        this.requestReceiver = new RequestReceiver();
    }

    public String handleAnswer(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        String USER_ID = "-";
        String userID_for_test = "1234";

        switch (header.code) {
            case Header.CODE_USER_ID:
                System.out.println("아이디 입력 받음");
                String user_id = bodyReader.readUTF();
                UserService userService = new UserService();

                BodyMaker user_id_bodyMaker = new BodyMaker();
                user_id_bodyMaker.addStringBytes(user_id);

                if (!userService.idCheck(user_id))          //저장한 user_id를 체크함
                {
                    System.out.println("중복 안됨");
                    byte[] id_resBody_unDup = user_id_bodyMaker.getBody();
                    Header id_resHeader_unDup = new Header(     //중복 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_SUCCESS,    //중복아닐 떈 success
                            id_resBody_unDup.length);

                    outputStream.write(id_resHeader_unDup.getBytes());
                    outputStream.write(id_resBody_unDup);

                } else {
                    System.out.println("중복됨");
                    byte[] id_resBody_Dup = user_id_bodyMaker.getBody();
                    Header id_resHeader_Dup = new Header(         //실패 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_FAIL,   //중복일 땐 fail
                            id_resBody_Dup.length);

                    outputStream.write(id_resHeader_Dup.getBytes());
                    outputStream.write(id_resBody_Dup);
                }
                USER_ID = user_id;
                break;

            case Header.CODE_USER_DTO: // CODE_USER_INFO랑 같은 기능임
            case Header.CODE_USER_INFO:
                MyUserDAO myUserDAO = new MyUserDAO();
                UserService us = new UserService();
                UserDTO signUpUser = UserDTO.read(bodyReader);

                switch (signUpUser.getUser_category()) {
                    case 1:
                        signUpUser.setUser_state(false);
                        break;
                    default:
                        break;
                }

                myUserDAO.userAdd(signUpUser);         //전송받은 UserDTO를 추가
                BodyMaker userInfo_bodyMaker = new BodyMaker();
                userInfo_bodyMaker.add(signUpUser);
                byte[] signup_body_success = userInfo_bodyMaker.getBody();

                Header resHeader = new Header(            //성공 결과 전송
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        signup_body_success.length);
                outputStream.write(resHeader.getBytes());
                outputStream.write(signup_body_success);

                USER_ID = signUpUser.getUser_id();
                break;

            case Header.CODE_USER_PW:
                String userID = bodyReader.readUTF(), userPW = bodyReader.readUTF();
                UserService userService_use_pw = new UserService();

                if (userService_use_pw.pwCheck(userID, userPW)) {  //id, pw이용해서 비번확인
                    BodyMaker user_pw_bodyMaker = new BodyMaker();
                    MyStoreDAO myStoreDAO = new MyStoreDAO();
                    UserDTO loginUser = userService_use_pw.findUser(userID);
                    user_pw_bodyMaker.add(loginUser);   //유저 DTO 먼저 넣음

                    if (loginUser.getUser_category() == 1)   //점주면 해당 유저소유 StoreDTO를 뒤에 더 넣어줌
                    {
                        List<StoreDTO> userStores = myStoreDAO.selectByUserId(userID);
                        user_pw_bodyMaker.addIntBytes(userStores.size());

                        for (StoreDTO userStore : userStores) {
                            user_pw_bodyMaker.add(userStore);
                        }
                    }


                    byte[] pw_resBody_Success = user_pw_bodyMaker.getBody();

                    Header pw_resHeader = new Header(       //성공결과 전송
                            Header.TYPE_RES,
                            Header.CODE_SUCCESS,
                            pw_resBody_Success.length);
                    outputStream.write(pw_resHeader.getBytes());
                    outputStream.write(pw_resBody_Success);
                } else {
                    BodyMaker bodyMaker = new BodyMaker();
                    byte[] pw_resBody_Fail = bodyMaker.getBody();
                    Header pw_resHeader = new Header(        //실패 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_FAIL,
                            pw_resBody_Fail.length);
                    outputStream.write(pw_resHeader.getBytes());
                    outputStream.write(pw_resBody_Fail);
                }
                USER_ID = userID;
                break;


            case Header.CODE_FIXED_ORDER_DTO:
                MyOrderDAO myOrderDAO = new MyOrderDAO();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                int order_id = bodyReader.readInt();
                String user = bodyReader.readUTF();
                int store_id = bodyReader.readInt();
                long order_price = bodyReader.readLong();
                String order_state = bodyReader.readUTF();
                LocalDateTime order_orderTime = LocalDateTime.parse(bodyReader.readUTF(), formatter);
                String order_num = bodyReader.readUTF();

                OrderDTO orderDTO = new OrderDTO(order_id, user, store_id, order_price, order_state, order_orderTime, order_num);
                boolean isSuccess = true;
                if (orderDTO.getOrder_state().equals("배달중"))
                    myOrderDAO.updateOrderState_Delivery(orderDTO.getOrder_id());
                else if (orderDTO.getOrder_state().equals("취소"))
                    myOrderDAO.updateOrderState_Cancle(orderDTO.getOrder_id());
                else
                    isSuccess = false;

                if (isSuccess == true) {
                    System.out.println("주문 승인/거절 성공");
                    Header rs_header = new Header(
                            Header.TYPE_RES,
                            Header.CODE_SUCCESS,
                            0);
                    outputStream.write(rs_header.getBytes());
                } else {
                    System.out.println("주문 승인/거절 실패");
                    Header rs_header = new Header(
                            Header.TYPE_RES,
                            Header.CODE_FAIL,
                            0);
                    outputStream.write(rs_header.getBytes());
                }
                break;
            case Header.CODE_REPLY_CONTENTS:
                MyReviewDAO myReviewDAO = new MyReviewDAO();
                int r_review_id = bodyReader.readInt();
                int r_order_id = bodyReader.readInt();
                int r_store_id = bodyReader.readInt();
                String r_menu_name = bodyReader.readUTF();
                String r_review_content = bodyReader.readUTF();
                int review_rate = bodyReader.readInt();
                int review_comment = bodyReader.readInt();

                ReviewDTO reply = new ReviewDTO(r_store_id, userID_for_test, r_order_id, review_rate,
                        r_review_content, LocalDateTime.now(), "1", r_review_id);
                reply.toString();
                int result = myReviewDAO.insertReview(reply);

                if (result == 1) {
                    System.out.println("답글 등록 성공");
                    Header rs_header = new Header(
                            Header.TYPE_RES,
                            Header.CODE_SUCCESS,
                            0);
                    outputStream.write(rs_header.getBytes());
                } else {
                    System.out.println("답글 등록 실패");
                    Header rs_header = new Header(
                            Header.TYPE_RES,
                            Header.CODE_FAIL,
                            0);
                    outputStream.write(rs_header.getBytes());
                }


                break;

            case Header.CODE_MENU_INFO:
                MyMenuDAO myMenuDAO = new MyMenuDAO();
                MenuService miMs = new MenuService();
                MenuDTO addMenu = MenuDTO.read(bodyReader);
                addMenu.setMenu_state(false);

                if(miMs.isDupNAme(addMenu)) //이름 중복 시
                {
                    Header menuadd_resHeader = new Header(            //실패 결과 전송
                            Header.TYPE_RES,
                            Header.CODE_FAIL,
                            0);
                    outputStream.write(menuadd_resHeader.getBytes());
                    break;
                }

                myMenuDAO.menuAdd(addMenu);         //전송받은 MenuDTO를 추가
                System.out.println("메뉴 넣음");

                BodyMaker menuInfo_bodyMaker = new BodyMaker();
                menuInfo_bodyMaker.add(addMenu);
                byte[] menuadd_body_success = menuInfo_bodyMaker.getBody();

                Header menuadd_resHeader = new Header(            //성공 결과 전송
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        menuadd_body_success.length);
                outputStream.write(menuadd_resHeader.getBytes());
                outputStream.write(menuadd_body_success);
                break;
            case Header.CODE_ACCEPT_USER_ID:
                requestSender.sendHowAcceptUserReq(outputStream);
                break;
            case Header.CODE_HOW_ACCEPT_USER:
                UserService userService1 = new UserService();
                String ur_id = bodyReader.readUTF();
                boolean state = bodyReader.readBoolean();

                if (state) {
                    userService1.updateUser_Accept(ur_id);
                } else
                    userService1.deleteUser(ur_id);

                List<UserDTO> users = userService1.selectUser_WaitingAccept();
                BodyMaker bodyMaker = new BodyMaker();
                bodyMaker.addIntBytes(users.size());
                for (UserDTO wuser : users) {
                    bodyMaker.add(wuser);
                }

                byte[] WuserBody = bodyMaker.getBody();
                Header accept_header = new Header(
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        WuserBody.length);
                outputStream.write(accept_header.getBytes());
                outputStream.write(WuserBody);
                break;
            case Header.CODE_ACCEPT_STORE_ID:
                requestSender.sendHowAcceptStoreReq(outputStream);
                break;
            case Header.CODE_HOW_ACCEPT_STORE:
                StoreService storeService = new StoreService();
                int str_id = bodyReader.readInt();
                boolean str_state = bodyReader.readBoolean();

                if (str_state) {
                    storeService.updateStore_Accept(str_id);
                } else
                    storeService.deleteStore(str_id);

                List<StoreDTO> stors = storeService.selectStore_WaitingAccept();
                BodyMaker bodyMaker1 = new BodyMaker();
                bodyMaker1.addIntBytes(stors.size());
                for (StoreDTO wstore : stors) {
                    bodyMaker1.add(wstore);
                }

                byte[] WStoreBody = bodyMaker1.getBody();
                Header accept_header1 = new Header(
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        WStoreBody.length);
                outputStream.write(accept_header1.getBytes());
                outputStream.write(WStoreBody);
                break;

            case Header.CODE_INSERT_MENU_OPTION:
                int imoMenu_id = bodyReader.readInt();
                int selectOptionsLength = bodyReader.readInt();
                MyMenuOptionDAO myMenuOptionDAO = new MyMenuOptionDAO();
                MenuService imoMs = new MenuService();
                List<OptionDTO> imoOptions = new ArrayList<OptionDTO>();
                List<MenuOptionDTO> imoMenuOptions = new ArrayList<MenuOptionDTO>();
                MenuOptionDTO addMODTO = new MenuOptionDTO();

                for(int i = 0; i < selectOptionsLength; i++)
                {
                    imoOptions.add(OptionDTO.read(bodyReader));
                }

                for(OptionDTO imoOption : imoOptions)
                {
                    addMODTO = new MenuOptionDTO();
                    addMODTO.setMenu_id(imoMenu_id);
                    addMODTO.setOption_id(imoOption.getOption_id());
                    imoMenuOptions.add(addMODTO);
                }

                myMenuOptionDAO.menuOptionAdd(imoMs.menuOptionDupCheck(imoMenuOptions, imoMenu_id));

                List<OptionDTO> addedOptions = myMenuOptionDAO.selectMenuOptionsById(imoMenu_id);

                BodyMaker menuOption_bodyMaker = new BodyMaker();
                menuOption_bodyMaker.add(imoMs.selectMenuById(imoMenu_id));
                menuOption_bodyMaker.addIntBytes(addedOptions.size());

                for(OptionDTO addedOption : addedOptions)
                {
                    menuOption_bodyMaker.add(addedOption);
                }

                byte[] menuOption_body_success = menuOption_bodyMaker.getBody();
                Header menuOption_resHeader = new Header(            //성공 결과 전송
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        menuOption_body_success.length);
                outputStream.write(menuOption_resHeader.getBytes());
                outputStream.write(menuOption_body_success);
                break;

            case Header.CODE_STORE_TIME:
                int stStore_id = bodyReader.readInt();  //store_id, 새 시간 받기
                String newTime = bodyReader.readUTF();

                StoreDTO timeUptStore = new StoreDTO(); //받은 값들을 storeDTO에 담아서
                timeUptStore.setStore_time(newTime);
                timeUptStore.setStore_id(stStore_id);

                MyStoreDAO stmyStore = new MyStoreDAO();    //DAO를 이용해서 해당 가게 시간 변경
                stmyStore.updateStoreTime(timeUptStore);

                StoreDTO updateStore = stmyStore.selectById(stStore_id);

                BodyMaker storeTime_bodyMaker = new BodyMaker();
                storeTime_bodyMaker.add(updateStore);      //변경된 가게 정보 가져와서 바디에 저장 후
                byte[] storeTime_body_success = storeTime_bodyMaker.getBody();
                Header storeTime_resHeader = new Header(            //성공 결과 전송
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        storeTime_body_success.length);
                outputStream.write(storeTime_resHeader.getBytes());
                outputStream.write(storeTime_body_success);
                break;

            case Header.CODE_STORE_INFO:
                int store_idd = bodyReader.readInt();
                String user_idd = bodyReader.readUTF();
                String store_named = bodyReader.readUTF();
                String store_phoned = bodyReader.readUTF();
                String store_addressd = bodyReader.readUTF();
                boolean store_stated = bodyReader.readBoolean();
                int store_categoryd = bodyReader.read();
                int store_rated = bodyReader.read();
                String store_timed = bodyReader.readUTF();
                String store_infod = bodyReader.readUTF();
                StoreDTO addStore = new StoreDTO(store_idd, user_idd, store_named, store_phoned, store_addressd, store_stated, store_categoryd, store_rated, store_timed, store_infod);
                MyStoreDAO myStoreDAO = new MyStoreDAO();
                System.out.println(addStore.getUser_id());
                myStoreDAO.storeAdd(addStore);

                Header storeAdd_resHeader = new Header(            //성공 결과 전송
                        Header.TYPE_RES,
                        Header.CODE_SUCCESS,
                        0);
                outputStream.write(storeAdd_resHeader.getBytes());
        }
        return USER_ID;
    }
}
