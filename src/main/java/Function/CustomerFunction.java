package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyReviewDAO;
import service.ReviewService;
import view.ReviewView;

public class CustomerFunction
{
    private MyReviewDAO myReviewDAO;
    private ReviewService reviewService;
    private ReviewView reviewView;
    public CustomerFunction()
    {
        this.myReviewDAO = new MyReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        this.reviewService = new ReviewService(myReviewDAO);
        this.reviewView = new ReviewView();
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
