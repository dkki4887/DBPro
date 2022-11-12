import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyReviewDAO;
import persistence.dto.OrderDTO;
import persistence.dto.OrderMenuDTO;
import service.OrderService;
import service.ReviewService;
import service.StoreService;
import view.OrderView;
import view.ReviewView;

import java.util.List;

public class Main {
    public static void main(String args[]){

     /*   *//* Order Test *//*
        MyOrderDAO myOrderDAO = new MyOrderDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OrderService orderService = new OrderService(myOrderDAO);
        OrderView orderView = new OrderView();

        orderService.selectOrder_store(1);
        orderView.printMyOrder(orderService.selectOrder_store(1));
        *//* Order Test End *//*
*/
//        for(OrderDTO orderDTO : orderDTOS)
//            System.out.println("dto.toString() = " + orderDTO.toString());


        /* Review Test */
        MyReviewDAO myReviewDAO = new MyReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ReviewService reviewService = new ReviewService(myReviewDAO);
        ReviewView reviewView = new ReviewView();

    /*    //insert review
        int reviewResult=reviewService.insertReview(2,2,"asd",2);
        if(reviewResult ==1) System.out.println("리뷰 작성 성공");
        else System.out.println("리뷰 작성 실패");
*/
        //print review
        String review_user_id = "abc"; // 고객 id로 review select
        reviewView.printMyReview( reviewService.findReviewWithUserIdLike( review_user_id ) );

        /* Review Test END*/

    }
}