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

    public List<OrderDTO> findOrderWithCustomerIdLike(Long cust_id )
    {
        List<OrderDTO> orderDTOS = orderDAO.findOrderWithCustomerIdLike(cust_id);
        return orderDTOS;
    }

    public List<OrderDTO> findOrderWithStoreIdLike(Long store_id )
    {
        List<OrderDTO> orderDTOS = orderDAO.findOrderWithCustomerIdLike(store_id);
        return orderDTOS;
    }

    public int updateOrder(Long order_id)
    {
        List<OrderDTO> dtos= orderDAO.findOrderWithOrderID(order_id);
        if( dtos == null) return -1;
        else
        {
            OrderDTO orderDTO =dtos.get(0);
            return orderDAO.updateOrder(orderDTO);
        }
    }

    public List<OrderDTO> selectOrder_store(int store_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_store(store_id);

        return orderDTOS;
    }



}
