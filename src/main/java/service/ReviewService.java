package service;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyReviewDAO;
import persistence.dto.ReviewDTO;
import persistence.dto.Review_omDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ReviewService {
    private final MyReviewDAO reviewDAO;// = new ReviewDAO();   or   constuct

    public ReviewService() {
        reviewDAO = new MyReviewDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    public int insertReview(int store_id , String user_id , int order_id)
    {
        Scanner sc =new Scanner(System.in);
        LocalDateTime current_time = LocalDateTime.now();
        System.out.println("별점을 입력해주세요 : ");
        int grade = sc.nextInt();
        System.out.println("리뷰을 입력해주세요 : ");
        String review_content = sc.nextLine();
        review_content =sc.nextLine();
        ReviewDTO reviewDTO = new ReviewDTO(store_id,user_id,order_id,grade,review_content,current_time);
        int result =reviewDAO.insertReview(reviewDTO);
        return result;
    }

    public List<Review_omDTO> findReviewWithUserIdLike(String user_id)
    {
        List<Review_omDTO> reviewDTOS = reviewDAO.findReviewWithUserIdLike(user_id);
        return reviewDTOS;
    }
}
