package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Review_omDTO {
    private int order_id;
    private int store_id;
    private String menu_name;
    private int menu_price;
    private String review_content;
    private int review_rate;

}