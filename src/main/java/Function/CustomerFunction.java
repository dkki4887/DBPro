package Function;

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

public class CustomerFunction
{
    public CustomerFunction()
    {
    }

    public void inquireOrder(String customer_id)
    {
        MyOrderDAO myOrderDAO = new MyOrderDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OrderService orderService = new OrderService(myOrderDAO);
        OrderView orderView = new OrderView();
        orderView.printOrder( orderService.selectOrder_customer(customer_id) );
    }


    public void writeReview(String customer_id)
    {
        MyOrderDAO myOrderDAO = new MyOrderDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OrderService orderService = new OrderService(myOrderDAO);
        OrderView orderView = new OrderView();
        Scanner sc = new Scanner(System.in);
        MyReviewDAO myReviewDAO = new MyReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReviewService reviewService = new ReviewService(myReviewDAO);
        ReviewView reviewView = new ReviewView();

        orderView.printOrderWithID(orderService.selectOrder_customer(customer_id)); //order_id 포함한 주문출력
        System.out.print("리뷰를 작성할 주문번호를 입력하십시오 : ");
        int input_orderID = sc.nextInt();

        int store_id = -1;
        List<OrderDTO> custOrder_List = orderService.selectOrder_customer(customer_id);
        for(OrderDTO ods: custOrder_List) // 입력한 order_id와 일치하는 주문의 store_id 받아옴
        {
           if(ods.getOrder_id() == input_orderID)
           {
               store_id = ods.getStore_id();
           }
        }
        if(store_id==-1) System.out.println("해당하는 주문번호가 없습니다.");
        else
        {
            int reviewResult=reviewService.insertReview(store_id,customer_id,input_orderID);
            if(reviewResult ==1) System.out.println("리뷰 작성 성공");
            else System.out.println("리뷰 작성 실패");
        }
    }

    public void inquireReview(String user_id)
    {
        MyReviewDAO myReviewDAO = new MyReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReviewService reviewService = new ReviewService(myReviewDAO);
        ReviewView reviewView = new ReviewView();

        reviewView.printMyReview( reviewService.findReviewWithUserIdLike( user_id ) );
    }

    public void createOrder(String user_id) //주문 생성 메소드
    {
        Scanner sc = new Scanner(System.in);
        MyStoreDAO sDAO = new MyStoreDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        StoreService ss = new StoreService();
        StoreView sv = new StoreView();

        MyMenuDAO mDAO = new MyMenuDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        MenuService ms = new MenuService();
        MenuView mv = new MenuView();

        sv.printAllStore(ss.selectAllStoreNameAndId());
        System.out.print("주문할 가게의 번호를 선택하세요: ");
        int store_id = sv.selectStore(ss.selectAllStoreNameAndId(), sc.nextInt());
        mv.printStoreAllMenu(ms.selectStoreMenu(store_id));
        System.out.print("주문할 메뉴의 번호를 선택하세요: ");
    }




}
