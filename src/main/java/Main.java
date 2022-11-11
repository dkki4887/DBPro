import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import persistence.dto.OrderDTO;
import service.StoreService;

import java.util.List;

public class Main {
    public static void main(String args[]){

        MyOrderDAO myOrderDAO = new MyOrderDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        List<OrderDTO> orderDTOS = myOrderDAO.selectOrder_store(1);

        for(OrderDTO orderDTO : orderDTOS)
            System.out.println("dto.toString() = " + orderDTO.toString());
    }
}