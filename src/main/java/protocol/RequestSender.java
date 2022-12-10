package protocol;

import inputManager.StoreInputManager;
import persistence.dto.OptionDTO;
import persistence.dto.StoreDTO;

import javax.swing.text.html.Option;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RequestSender {

    public void sendUserIDReq(DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        byte[] resBody = bodyMaker.getBody();
        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_ID,
                0
        );

        outputStream.write(header.getBytes());
        outputStream.write(resBody);
    }
/*
    public void sendUserPWReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_PW,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserNameReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_NAME,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserAddressReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_ADDRESS,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserPhoneReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_PHONE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserTypeReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_TYPE,
                0
        );

        outputStream.write(header.getBytes());
    }
*/

    public void sendUserInfoReq(String user_id, DataOutputStream outputStream) throws IOException {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);

        byte[] resBody = bodyMaker.getBody();
        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_USER_INFO,  //유저 정보 코드
                resBody.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(resBody);
    }
/*
    public void sendStoreIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_ID,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendStoreNameReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_NAME,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreAddressReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_ADDRESS,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStorePhoneReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_PHONE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreCategoryReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_CATEGORY,
                0
        );

        outputStream.write(header.getBytes());
    }
    */

    public void sendStoreTimeReq(StoreDTO storeDTO, DataOutputStream outputStream) throws IOException {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(storeDTO);

        byte[] stbody = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_TIME,
                stbody.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(stbody);
    }

    public void sendMenuOptionIDReq(List<OptionDTO> optionDTOS, DataOutputStream outputStream) throws IOException {

        BodyMaker ieo_bodyMaker = new BodyMaker();
        ieo_bodyMaker.addIntBytes(optionDTOS.size());

        for(OptionDTO imoOption : optionDTOS)
            ieo_bodyMaker.add(imoOption);

        byte[] options_body = ieo_bodyMaker.getBody();
        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_OPTION,
                options_body.length
        );
        outputStream.write(header.getBytes());
        outputStream.write(options_body);
    }
    /*

    public void sendStoreOpenReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_OPEN,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreCloseReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_CLOSE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreIntroduceReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_INTRODUCE,
                0
        );

        outputStream.write(header.getBytes());
    }
*/

    public void sendStoreInfoReq(DataOutputStream outputStream) throws IOException {

        Header StoreInfoReqheader = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_INFO,//가게 정보 전송 요청
                0
        );
        outputStream.write(StoreInfoReqheader.getBytes());

    }

    public void sendStoreStateReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_STATE,
                0
        );

        outputStream.write(header.getBytes());
    }
/*    public void sendStoreDiscountRateReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
               // Header.CODE_STORE_DISCOUNT_RATE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreTotalSalesReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_TOTAL_SALES,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendStoreCountSalesReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_STORE_COUNT_OF_SALE,
                0
        );

        outputStream.write(header.getBytes());
    }

    */

    public void sendMenuInfoReq(DataOutputStream outputStream) throws IOException {
        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_INFO,
                0
        );
        outputStream.write(header.getBytes());
    }

    public void sendAcceptUserNumReq(DataOutputStream outputStream) throws IOException {
            System.out.println("요청 보냄");
            Header header = new Header(
                    Header.TYPE_REQ,
                    Header.CODE_ACCEPT_USER_NUM,
                    0
            );
            outputStream.write(header.getBytes());
        }

    public void sendHowAcceptUserReq(DataOutputStream outputStream) throws IOException {
            Header header = new Header(
                    Header.TYPE_REQ,
                    Header.CODE_HOW_ACCEPT_USER,
                    0
            );
            outputStream.write(header.getBytes());

        }

        public void sendAcceptStoreNumReq(DataOutputStream outputStream) throws IOException {
            Header header = new Header(
                    Header.TYPE_REQ,
                    Header.CODE_ACCEPT_STORE_NUM,
                    0
            );
            outputStream.write(header.getBytes());
        }

        public void sendHowAcceptStoreReq(DataOutputStream outputStream) throws IOException {
            Header header = new Header(
                           Header.TYPE_REQ,
                           Header.CODE_HOW_ACCEPT_STORE,
                           0
                   );
                   outputStream.write(header.getBytes());
        }
    /*

    public void sendMenuListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_ID,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuNameReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_NAME,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuPriceReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_PRICE,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuStockReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_STOCK,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendMenuCategoryReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_MENU_CATEGORY,
                0
        );

        outputStream.write(header.getBytes());
    }
    public void sendOptionListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_OPTION_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOptionIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_OPTION_ID,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOptionPriceReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_OPTION_PRICE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_ID,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderStateReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_STATE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderMenuListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_MENU_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendOrderPriceReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_ORDER_PRICE,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendReviewContentReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_CONTENT,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendReviewListReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_LIST,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendReviewIDReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_ID,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendReviewAnserReq(Scanner s, DataOutputStream outputStream) throws IOException {

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_ANS,
                0
        );

        outputStream.write(header.getBytes());
    }

    public void sendUserIDCheck(String user_id, DataOutputStream outputStream) throws IOException {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQ,
                Header.CODE_REVIEW_ANS,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

     */

    public void sendStoreTimeAns(int store_id, DataOutputStream outputStream) throws IOException {
        Scanner sc = new Scanner(System.in);
        StoreInputManager sim = new StoreInputManager();
        String newTime = sim.inputStore_Time(sc);

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(store_id);
        bodyMaker.addStringBytes(newTime);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_TIME,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreInfoAns(String user_id, DataOutputStream outputStream) throws IOException {
        StoreInputManager addStoreInfoManager = new StoreInputManager();
        StoreDTO addStoreInfo = addStoreInfoManager.getAddStoreInfo(); //가게 정보 입력 후 전송
        addStoreInfo.setUser_id(user_id);

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(addStoreInfo);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_INFO,//임시 코드 가게 정보 전송
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserIDAns(Scanner s, DataOutputStream outputStream) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요 : ");
        String id = sc.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
        System.out.println("아이디 입력 보냄");
    }

    public void sendUserPWAns(Scanner s,String user_id, DataOutputStream outputStream) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("비밀번호를 입력하세요 : ");
        String pw = sc.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(user_id);
        bodyMaker.addStringBytes(pw);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_PW,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendAddMenuOptionAns(List<OptionDTO> options ,int Menu_id, DataOutputStream outputStream) throws IOException {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(Menu_id);
        bodyMaker.addIntBytes(options.size());

        for(OptionDTO option: options)
        {
            bodyMaker.add(option);
        }
        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_INSERT_MENU_OPTION,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

}