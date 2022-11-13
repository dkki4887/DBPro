package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import service.OrderService;
import view.OrderView;

public class StorekeeperFunction {
    private OrderService orderService;
    private OrderView orderView;

    public StorekeeperFunction() {
        this.orderService = new OrderService();
        this.orderView = new OrderView();
    }

    public void selectOrder_store(int store_id) {
        orderView.printOrder(orderService.selectOrder_store(store_id));
    }
}