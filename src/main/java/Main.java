import Function.CustomerFunction;
import Function.LoginFunction;
import Function.ManagerFunction;
import Function.StorekeeperFunction;
import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyReviewDAO;
import persistence.dto.OrderDTO;
import persistence.dto.OrderMenuDTO;
import service.MenuService;
import service.OrderService;
import service.ReviewService;
import service.StoreService;
import view.OrderView;
import view.ReviewView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        CustomerFunction customer = new CustomerFunction();
        LoginFunction login = new LoginFunction();
        ManagerFunction manager = new ManagerFunction();
        StorekeeperFunction keeper = new StorekeeperFunction();
        int store_id=1;
        String user1_id="user1";
        String user2_id ="user2";

        //사장 계정 등록
        login.userAdd();
        //매장등록
        System.out.print("가게를 등록할 계정의 아이디를 입력하시오: ");
        String Oner_id = sc.nextLine();
        keeper.requestStoreAdd(Oner_id);
        //매장승인
        manager.storeAccept();
        //매장조회
        manager.selectStore_Accepted();
        //옵션등록 하는중

        //메뉴등록 =>limit 1짜리 하나 있어야함(재료소진) , 가능옵션 입력

        //메뉴조회 (사장)
        keeper.viewStoreAllMenu(store_id);
        //메뉴수정 (메뉴명, 가격)
        keeper.menuUpdate(store_id);
        //주문생성 +재료소진  4 +2
        customer.createOrder(user1_id); // 3번
        customer.createOrder(user1_id);
        customer.createOrder(user1_id);
        customer.createOrder(user2_id); // 3번
        customer.createOrder(user2_id);
        customer.createOrder(user2_id);
        //주문조회
        keeper.selectOrder_store(store_id);
        //주문접수 (승인, 거절)
        keeper.acceptOrder(store_id);
        //주문취소(고객)
        customer.cancleOrder(user1_id);
        customer.cancleOrder(user2_id);
        //주문상태 변경(배달완료)
        keeper.deliveryFinish(store_id);
        //고객 주문이력조회 (취소, 배달완료 출력)
        customer.selectOrder_customer(user1_id);
        //리뷰작성
        customer.writeReview(user1_id);
        //리뷰조회
        customer.inquireReview(user1_id);
    }
}