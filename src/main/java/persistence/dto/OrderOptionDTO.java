package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
public class OrderOptionDTO {
    private int orderMenu_id;
    private String option_name;
    private long option_price;
}
