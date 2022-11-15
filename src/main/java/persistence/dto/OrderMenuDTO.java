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
    private int order_id;
    private String menu_name;

    public OrderMenuDTO(int orderMenu_id, int order_id, String menu_name)
    {
        this.orderMenu_id = orderMenu_id;
        this.order_id = order_id;
        this.menu_name = menu_name;
    }

    public void setOrderMenu_id(int orderMenu_id) {
        this.orderMenu_id = orderMenu_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getOrderMenu_id() {
        return orderMenu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }
}
