package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderDTO {
    private int order_id;
    private String user_id;
    private int store_id;
    private Long order_price;
    private String order_payment;
    private String order_state;
    private LocalDateTime order_orderTime;
    private LocalDateTime order_receiveTime;
}