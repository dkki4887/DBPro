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
}
