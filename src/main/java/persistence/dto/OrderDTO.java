package persistence.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderDTO {
    private int order_id;
    private String user_id;
    private int store_id;
    private long order_price;
    private String order_state;
    private String order_orderTime;
    private List<OrderMenuDTO> orderMenuList;
    private List<OrderOptionDTO> orderOptionList;

    public OrderDTO(){};

    public OrderDTO(String user_id, int store_id, long order_price, LocalDateTime order_orderTime) {
        this.user_id = user_id;
        this.store_id = store_id;
        this.order_price = order_price;
        this.order_state = "접수 대기";
        this.order_orderTime = order_orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));;
    }

    public OrderDTO(String user_id, LocalDateTime order_orderTime)
    {
        this.user_id = user_id;
        this.order_orderTime = order_orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));;
    }

    public OrderDTO(int order_id) {
        this.order_id=order_id;
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

    public long getOrder_price() {
        return order_price;
    }

    public String getOrder_state() {
        return order_state;
    }

    public String getOrder_orderTime() {
        return order_orderTime;
    }

    public List<OrderMenuDTO> getOrderMenuList() {
        return orderMenuList;
    }

    public List<OrderOptionDTO> getOrderOptionList() {
        return orderOptionList;
    }
}
