package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewDTO {
    private int review_id;
    private int store_id;
    private String user_id;
    private int order_id;
    private int review_rate;
    private String review_content;
    private LocalDateTime review_time;

    public ReviewDTO() {}

    public ReviewDTO(int review_id,int store_id , String user_id , int order_id,int review_rate ,String review_content, LocalDateTime review_time)
    {
        this.review_id = review_id;
        this.store_id=store_id;
        this.user_id=user_id;
        this.order_id=order_id;
        this.review_rate=review_rate;
        this.review_content=review_content;
        this.review_time=review_time;
    }

}