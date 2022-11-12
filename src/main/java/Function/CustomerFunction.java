package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyReviewDAO;
import service.OrderService;
import service.ReviewService;
import view.OrderView;
import view.ReviewView;

import java.util.Scanner;

public class CustomerFunction
{
    private MyReviewDAO myReviewDAO;
    private ReviewService reviewService;
    private ReviewView reviewView;

    private MyOrderDAO myOrderDAO;
    private OrderService orderService;
    private OrderView orderView;
    public CustomerFunction()
    {
        this.myReviewDAO = new MyReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        this.reviewService = new ReviewService(myReviewDAO);
        this.reviewView = new ReviewView();

        this.myOrderDAO = new MyOrderDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        this.orderService = new OrderService(myOrderDAO);
        this.orderView = new OrderView();
    }

    public void inquireOrder(String user_id)
    {
        orderView.printMyOrder(orderService.selectOrder_customer(user_id));
    }

    public void writeReview(int review_id , int store_id , String user_id , int order_id)
    {
        int reviewResult=reviewService.insertReview(review_id,store_id,user_id,order_id);
        if(reviewResult ==1) System.out.println("리뷰 작성 성공");
        else System.out.println("리뷰 작성 실패");
    }

    public void inquireReview(String user_id)
    {
        reviewView.printMyReview( reviewService.findReviewWithUserIdLike( user_id ) );
    }




}
