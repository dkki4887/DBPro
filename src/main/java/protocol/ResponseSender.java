package protocol;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ResponseSender {

    public void sendUserIDAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("아이디를 입력하세요 : ");
        String id = s.nextLine();

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
    }

    public void sendUserPWAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("비밀번호를 입력하세요 : ");
        String pw = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
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

    public void sendUserNameAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("이름을 입력하세요 : ");
        String name = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(name);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_NAME,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserAddressAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("주소을 입력하세요 : ");
        String address = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(address);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_ADDRESS,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserPhoneAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("전화번호를 입력하세요 : ");
        String phone = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(phone);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_PHONE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendUserTypeAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("계정 유형을 입력하세요(0:관리자, 1:점주, 2:개인) : ");
        int userType = s.nextInt();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(userType);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_USER_TYPE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreNameAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 이름을 입력하세요 : ");
        String store_name = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(store_name);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_NAME,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreIDAns(int store_id, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(store_id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_ID,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreAddressAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 주소을 입력하세요 : ");
        String address = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(address);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_ADDRESS,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStorePhoneAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 전화번호를 입력하세요 : ");
        String phone = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(phone);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_PHONE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreCategoryAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 카테고리를 입력하세요. : ");
        String storeCatogory = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(storeCatogory);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_CATEGORY,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreStateAns(boolean store_state, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addBooleanBytes(store_state);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_STATE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreOpenAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 오픈시간을 입력하세요. : ");
        String storeOpen = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(storeOpen);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_OPEN,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreCloseAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 마감시간을 입력하세요. : ");
        String storeClose = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(storeClose);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_CLOSE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreIntroduceAns(Scanner s, DataOutputStream outputStream) throws IOException {

        System.out.print("가게 한 줄 소개를 입력하세요. : ");
        String storeIntroduce = s.nextLine();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(storeIntroduce);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_INTRODUCE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreSaleCountAns(int count, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(count);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_COUNT_OF_SALE,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreTotalSalesAns(int totalSales, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addIntBytes(totalSales);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_TOTAL_SALES,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void sendStoreTimeAns(String time, DataOutputStream outputStream) throws IOException {

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(time);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_ANS,
                Header.CODE_STORE_TIME,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }
}
