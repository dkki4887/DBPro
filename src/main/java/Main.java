import Function.CustomerFunction;
import Function.LoginFunction;
import Function.StorekeeperFunction;
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
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
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

        CustomerFunction c = new CustomerFunction();
//        c.createOrder("user1");
//        LoginFunction lf = new LoginFunction();
//        lf.Login(sc);
        StorekeeperFunction s = new StorekeeperFunction();
        c.inquireReview("user1");
    }
}