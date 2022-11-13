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

    public OrderDTO(String user_id, int store_id) {
        this.user_id = user_id;
        this.store_id = store_id;
        this.order_state = "접수 대기";
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public Long getOrder_price() {
        return order_price;
    }

    public String getOrder_payment() {
        return order_payment;
    }

    public String getOrder_state() {
        return order_state;
    }

    public LocalDateTime getOrder_orderTime() {
        return order_orderTime;
    }

    public LocalDateTime getOrder_receiveTime() {
        return order_receiveTime;
    }

    public List<OrderMenuDTO> getOrderMenuList() {
        return orderMenuList;
    }

    public List<OrderOptionDTO> getOrderOptionList() {
        return orderOptionList;
    }
}
