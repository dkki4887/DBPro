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
}