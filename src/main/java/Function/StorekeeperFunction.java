package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import service.OrderService;
import view.OrderView;

import java.util.Scanner;

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


    public void deliveryFinish()
    {
        Scanner sc= new Scanner(System.in);

        System.out.print("조회할 가게번호를 입력하시오. : ");
        int store_id = sc.nextInt();

        orderView.printOrderWithID(orderService.selectOrder_store(store_id));

        System.out.print("배달완료할 주문 번호를 입력하시오 : ");
        int order_id = sc.nextInt();

        orderService.updateOrderState_Complete(order_id);
    }


}