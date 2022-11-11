package view;

import persistence.dto.ReviewDTO;

import java.util.List;
import java.util.Scanner;

public class ReviewView {
    public void printAll2(List<ReviewDTO> dtos){
        for(ReviewDTO dto: dtos){
            System.out.println("dto.toString() = " + dto.toString());
        }

    }

    public void printMyReview(List<ReviewDTO> dtos){
        Scanner sc =new Scanner(System.in);
        int reviewCount = dtos.size();
        System.out.println("==========내가 작성한 리뷰==========");

        while(true)
        {
            System.out.println("조회할 페이지 입력  (MAX : 2) (else exit) : ");
            int inputPage = sc.nextInt();
            if(inputPage ==1)
            {
                System.out.println("=============================");
                for(int i = 0 ; i < reviewCount/2 ; i ++)
                {
                    ReviewDTO dto = dtos.get(i);
                    System.out.println("가게 :" + dto.getStore_id());
                    System.out.println("리뷰 :" + dto.getReview_content());
                    System.out.println("별점 :" + dto.getReview_rate());
                    System.out.println("=============================");
                }
            }
            else if ( inputPage ==2)
            {
                System.out.println("=============================");
                for(int i = reviewCount/2 ; i < reviewCount ; i ++)
                {
                    ReviewDTO dto = dtos.get(i);
                    System.out.println("가게 :" + dto.getStore_id());
                    System.out.println("리뷰 :" + dto.getReview_content());
                    System.out.println("별점 :" + dto.getReview_rate());
                    System.out.println("=============================");
                }
            }
            else
                break;
        }
    }
}
