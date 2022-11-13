package service;

import persistence.dao.MyOrderDAO;
import persistence.dto.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class OrderService {
    private final MyOrderDAO orderDAO;

    public OrderService()
    {
        orderDAO = new MyOrderDAO();
    }

    public List<OrderDTO> selectAll()
    {
        List<OrderDTO> orderDTOS = orderDAO.selectAll();
        return orderDTOS;
    }

    public int insertOrder(String user_id , int store_id)
    {
        OrderDTO orderDTO = new OrderDTO(user_id, store_id);
        int result =orderDAO.insertOrder(orderDTO);
        return result;
    }

    public List<OrderDTO> selectOrder_store(int store_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_store(store_id);

        return orderDTOS;
    }

    public List<OrderDTO> selectOrder_customer(String user_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_customer(user_id);

        return orderDTOS;
    }



}
