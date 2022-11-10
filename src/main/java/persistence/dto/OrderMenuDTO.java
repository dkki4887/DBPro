package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMenuDTO {
    private int order_id;
    private int menu_id;
    private int option_id;
}
