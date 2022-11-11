package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuDTO {
    private int menu_id;
    private String menu_name;
    private int store_id;
    private Long menu_price;
    private int menu_quantity;
    private String menu_category;
}
