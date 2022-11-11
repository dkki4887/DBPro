package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("OrderMenuDTO")
public class OrderMenuDTO {
    private int order_id;
    private int menu_id;
    private long menu_price;
}
