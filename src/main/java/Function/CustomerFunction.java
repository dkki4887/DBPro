package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyReviewDAO;
import persistence.dao.MyStoreDAO;
import service.ReviewService;
import view.ReviewView;
import view.StoreView;

import java.util.Scanner;

public class CustomerFunction
{
    private MyReviewDAO myReviewDAO = new MyReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    private ReviewService reviewService = new ReviewService(myReviewDAO);
    private ReviewView reviewView = new ReviewView();
    Scanner sc = new Scanner(System.in);

    public CustomerFunction(){}

    public void writeReview(int review_id , int store_id , String user_id , int order_id)
    {
        int reviewResult=reviewService.insertReview(review_id,store_id,user_id,order_id);
        if(reviewResult == 1) System.out.println("리뷰 작성 성공");
        else System.out.println("리뷰 작성 실패");
    }

    public void inquireReview(String user_id)
    {
        reviewView.printMyReview( reviewService.findReviewWithUserIdLike( user_id ) );
    }

    public void createOrder(String user_id)
    {
        MyStoreDAO sDAO = new MyStoreDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        StoreView sv = new StoreView();
        sv.printAllStore(sDAO.selectAllStoreNameAndId());
        System.out.print("주문할 가게의 번호를 선택하세요: ");
        int store_id = sv.selectStore(sDAO.selectAllStoreNameAndId(), sc.nextInt());
    }




}
