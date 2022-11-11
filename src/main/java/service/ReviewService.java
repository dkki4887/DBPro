package service;

import persistence.dao.MyReviewDAO;
import persistence.dao.ReviewDAO;
import persistence.dto.ReviewDTO;

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

    public int insertReview(Long review_id , Long store_id , Long customer_id , Long order_id )
    {
        Scanner sc =new Scanner(System.in);
        LocalDateTime current_time = LocalDateTime.now();
        System.out.println("별점을 입력해주세요 : ");
        Long grade = sc.nextLong();
        System.out.println("리뷰을 입력해주세요 : ");
        String contents = sc.nextLine();
        contents =sc.nextLine();
        ReviewDTO reviewDTO = new ReviewDTO(review_id,store_id,customer_id,order_id,contents,current_time,grade);
        int result =reviewDAO.insertReview(reviewDTO);
        return result;
    }

    public List<ReviewDTO> findReviewWithCustomerIdLike(Long cust_id)
    {
        List<ReviewDTO> reviewDTOS = reviewDAO.findReviewWithCustomerIdLike(cust_id);
        return reviewDTOS;
    }
}
