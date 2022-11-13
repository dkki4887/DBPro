package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("orderOptionList")
public class OrderOptionDTO {
    private int orderMenu_id;
    private String option_name;

    public int getOrderMenu_id() {
        return orderMenu_id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOrderMenu_id(int orderMenu_id) {
        this.orderMenu_id = orderMenu_id;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }
}
