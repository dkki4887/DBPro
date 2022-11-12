package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import service.OrderService;
import view.OrderView;

public class StorekeeperFunction {
    private MyOrderDAO myOrderDAO;
    private OrderService orderService;
    private OrderView orderView;

    public StorekeeperFunction() {
        this.myOrderDAO = new MyOrderDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        this.orderService = new OrderService(myOrderDAO);
        this.orderView = new OrderView();
    }

    public void selectOrder_store(int store_id) {
        orderView.printMyOrder(orderService.selectOrder_store(store_id));
    }
}