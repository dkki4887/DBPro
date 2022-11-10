package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreDTO {
    private int store_id;
    private String user_id; //점주 누군지 확인용
    private String store_name;
    private String store_phone;
    private String store_address;
    private boolean store_state;
    private int store_category;
    private int store_rate;
    private String store_time;
    private String store_info;
}
