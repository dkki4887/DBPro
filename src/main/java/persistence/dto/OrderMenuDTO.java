package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
public class OrderMenuDTO {
    private int orderMenu_id;
    private int menu_name;
    private long menu_price;
}
