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

    public void selectOrder_store(int store_id)
    {
        orderView.printOrder(orderService.selectOrder_store(store_id));
    }

    public void deliveryFinish(int order_id)
    {
        //해당하는 주문이 배달중 상태인지 확인하는 코드 추가해야함
        orderService.updateOrderState_Complete(order_id);
    }

    public void acceptOrder(int order_id)
    {
        //해당 주문이 접수대기 상태인지 확인하는 코드 추가해야함

        System.out.println("주문 접수 : 1");
        System.out.println("주문 취소 : 2");
        int inputNum = sc.nextInt();
        if(inputNum ==1)
        {
            orderService.updateOrderState_Delivery(order_id);
        }
        else
        {
            orderService.updateOrderState_Cancle(order_id);
        }
    }
}