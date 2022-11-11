import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import persistence.dto.OrderDTO;
import persistence.dto.OrderMenuDTO;
import service.OrderService;
import service.StoreService;
import view.OrderView;

import java.util.List;

public class Main {
    public static void main(String args[]){

        MyOrderDAO myOrderDAO = new MyOrderDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OrderService orderService = new OrderService(myOrderDAO);
        OrderView orderView = new OrderView();

        orderService.selectOrder_store(1);
        orderView.printMyOrder(orderService.selectOrder_store(1));

//        for(OrderDTO orderDTO : orderDTOS)
//            System.out.println("dto.toString() = " + orderDTO.toString());
    }
}