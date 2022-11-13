package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("orderMenuList")
public class OrderMenuDTO {
    private int orderMenu_id;
    private String menu_name;

    public int getOrderMenu_id() {
        return orderMenu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }
}
