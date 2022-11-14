package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import service.OrderService;
import view.OrderView;

import java.util.Scanner;

public class StorekeeperFunction {
    private Scanner sc ;
    private OrderService orderService;
    private OrderView orderView;

    public StorekeeperFunction() {
        this.sc = new Scanner(System.in);
        this.orderService = new OrderService();
        this.orderView = new OrderView();
    }

    public void selectOrder_store(int store_id) {
        orderView.printOrder(orderService.selectOrder_store(store_id));
    }

    public void deliveryFinish(int order_id)
    {
        orderService.updateOrderState_Complete(order_id);
    }


}