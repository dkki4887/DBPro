package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OptionDTO {
    private int option_id;
    private long option_price;
    private String option_name;

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public long getOption_price() {
        return option_price;
    }

    public void setOption_price(long option_price) {
        this.option_price = option_price;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }
}
