package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<OrderMenuDTO> orderMenuList;
    private List<OrderOptionDTO> orderOptionList;
}