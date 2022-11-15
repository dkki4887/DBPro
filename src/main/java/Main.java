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
        CustomerFunction customer = new CustomerFunction();
        LoginFunction login = new LoginFunction();
        ManagerFunction manager = new ManagerFunction();
        StorekeeperFunction keeper = new StorekeeperFunction();
        int store_id=1;
        String user_id="user1";
        //매장등록

        //매장승인

        //옵션등록해야함
        //메뉴등록 =>limit 1짜리 하나 있어야함(재료소진)

        //가능한 옵션 등록 menuoption 테이블 사용

        //메뉴조회 (사장)

        //메뉴수정
        //keeper.menuUpdate(store_id);
        //주문생성 +재료소진
        customer.createOrder("user1");
        //주문조회
       // keeper.selectOrder_store(store_id);
        //주문접수 (승인, 거절)
        //keeper.acceptOrder(store_id);
        //주문취소
        //customer.cancleOrder(user_id);
        //주문상태 변경(배달완료)
        //keeper.deliveryFinish(store_id);
        //고객 주문이력조회 (취소, 배달완료 출력)
        //customer.selectOrder_customer(user_id);
        //리뷰작성
        //customer.writeReview(user_id);
        //리뷰조회
        //customer.inquireReview(user_id);
    }
}