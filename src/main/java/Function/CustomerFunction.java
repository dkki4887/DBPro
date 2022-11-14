package Function;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import persistence.MyBatisConnectionFactory;
import persistence.dao.MyMenuDAO;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyReviewDAO;
import persistence.dao.MyStoreDAO;
import persistence.dto.OrderDTO;
import service.MenuService;
import service.OrderService;
import service.ReviewService;
import service.StoreService;
import view.MenuView;
import view.OrderView;
import view.ReviewView;
import view.StoreView;

import java.util.Scanner;

import java.util.List;
import java.util.Scanner;

public class CustomerFunction {
    Scanner sc = new Scanner(System.in);

    public void inquireOrder(String customer_id) {
    }


    public void writeReview(String customer_id) {
        OrderService orderService = new OrderService();
        OrderView orderView = new OrderView();
        ReviewService reviewService = new ReviewService();

        orderView.printOrderWithID(orderService.selectOrder_customer(customer_id)); //order_id 포함한 주문출력
        System.out.print("리뷰를 작성할 주문번호를 입력하십시오 : ");
        int input_orderID = sc.nextInt();

        int store_id = -1;
        List<OrderDTO> custOrder_List = orderService.selectOrder_customer(customer_id);
        for (OrderDTO ods : custOrder_List) // 입력한 order_id와 일치하는 주문의 store_id 받아옴
        {
            if (ods.getOrder_id() == input_orderID) {
                store_id = ods.getStore_id();
            }
        }
        if (store_id == -1) System.out.println("해당하는 주문번호가 없습니다.");
        else {
            int reviewResult = reviewService.insertReview(store_id, customer_id, input_orderID);
            if (reviewResult == 1) System.out.println("리뷰 작성 성공");
            else System.out.println("리뷰 작성 실패");
        }
    }

    public void inquireReview(String user_id) {
        ReviewService reviewService = new ReviewService();
        ReviewView reviewView = new ReviewView();

        reviewView.printMyReview(reviewService.findReviewWithUserIdLike(user_id));
    }

    public void createOrder(String user_id) //주문 생성 메소드
    {
        StoreService ss = new StoreService();
        StoreView sv = new StoreView();

        MenuService ms = new MenuService();
        MenuView mv = new MenuView();

        OrderService os = new OrderService();

        sv.printAllStore(ss.selectAllStoreNameAndId());
        System.out.print("주문할 가게의 번호를 선택하세요: ");
        int store_id = sv.selectStore(ss.selectAllStoreNameAndId(), sc.nextInt());
        os.insertOrder(user_id, store_id);

        mv.printStoreAllMenu(ms.selectStoreMenu(store_id));
        System.out.print("주문할 메뉴의 번호를 선택하세요(1개 선택): ");
    }

    public void selectOrder_customer(String user_id) {
        OrderService orderService = new OrderService();
        OrderView orderView = new OrderView();

        orderView.printOrder(orderService.selectOrder_customer(user_id));
    }

    public void cancleOrder(int user_id)
    {
        OrderService odService = new OrderService();
        OrderView odView = new OrderView();

        System.out.print("취소할 주문의 번호를 입력하세요 : ");
        int order_id = sc.nextInt();

        // 배달중인지 확인하고 조건문으로 경우 분리하는 코드 필요

        odService.updateOrderState_Cancle(order_id);

    }



}
