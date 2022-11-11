package service;

import persistence.dao.MyReviewDAO;
import persistence.dao.ReviewDAO;
import persistence.dto.ReviewDTO;
import persistence.dto.Review_omDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ReviewService {
    private final MyReviewDAO reviewDAO ;// = new ReviewDAO();   or   constuct


    public ReviewService(MyReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    //    public List<ReviewDTO> findAll(){
//        List<ReviewDTO> reviewDTOS = reviewDAO.findAll();
//        return reviewDTOS;
//    }
    public List<ReviewDTO> selectAll()
    {
        List<ReviewDTO> reviewDTOS = reviewDAO.selectAll();
        return reviewDTOS;
    }

    public int insertReview(int review_id,int store_id , String user_id , int order_id ,String review_content)
    {
        Scanner sc =new Scanner(System.in);
        LocalDateTime current_time = LocalDateTime.now();
        System.out.println("별점을 입력해주세요 : ");
        int grade = sc.nextInt();
        System.out.println("리뷰을 입력해주세요 : ");
        String contents = sc.nextLine();
        contents =sc.nextLine();
        ReviewDTO reviewDTO = new ReviewDTO(review_id,store_id,user_id,order_id,grade,review_content,current_time);
        int result =reviewDAO.insertReview(reviewDTO);
        return result;
    }

    public List<Review_omDTO> findReviewWithUserIdLike(String user_id)
    {
        List<Review_omDTO> reviewDTOS = reviewDAO.findReviewWithUserIdLike(user_id);
        return reviewDTOS;
    }
}
