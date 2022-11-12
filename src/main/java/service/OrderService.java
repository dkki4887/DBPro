package service;

import org.apache.ibatis.session.SqlSession;
import persistence.dao.MyOrderDAO;
import persistence.dto.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class OrderService {
    private final MyOrderDAO orderDAO ;
    public OrderService(MyOrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<OrderDTO> selectAll()
    {
        List<OrderDTO> orderDTOS = orderDAO.selectAll();
        return orderDTOS;
    }

    public int insertOrder(Long order_id , Long customer_id , Long store_id , Long price, String type )
    {
        OrderDTO orderDTO = new OrderDTO(/*order_id,customer_id,store_id,price,type,LocalDateTime.now(),null,"접수대기"*/);
        int result =orderDAO.insertOrder(orderDTO);
        return result;
    }

    public List<OrderDTO> selectOrder_store(int store_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_store(store_id);

        return orderDTOS;
    }

    public List<OrderDTO> selectOrder_customer(String customer_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_customer(customer_id);

        return orderDTOS;
    }



}
