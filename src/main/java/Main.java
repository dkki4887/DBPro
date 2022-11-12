import Function.CustomerFunction;
import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyReviewDAO;
import persistence.dto.OrderDTO;
import persistence.dto.OrderMenuDTO;
import service.OrderService;
import service.ReviewService;
import service.StoreService;
import view.OrderView;
import view.ReviewView;

import java.util.List;

public class Main {
    public static void main(String args[]){

     /*   *//* Order Test *//*
        MyOrderDAO myOrderDAO = new MyOrderDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        OrderService orderService = new OrderService(myOrderDAO);
        OrderView orderView = new OrderView();

        orderService.selectOrder_store(1);
        orderView.printMyOrder(orderService.selectOrder_store(1));
        *//* Order Test End *//*
*/
//        for(OrderDTO orderDTO : orderDTOS)
//            System.out.println("dto.toString() = " + orderDTO.toString());
        CustomerFunction cf = new CustomerFunction();
        cf.createOrder("abc");
    }
}