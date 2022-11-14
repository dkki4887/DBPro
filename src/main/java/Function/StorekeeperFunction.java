package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyOrderDAO;
import persistence.dto.OrderDTO;
import service.OrderService;
import view.OrderView;

import java.util.List;
import java.util.Scanner;

public class StorekeeperFunction {
    private Scanner sc ;
    private OrderService orderService;
    private OrderView orderView;
    private int store_id;

    public StorekeeperFunction() {
        this.sc = new Scanner(System.in);
        this.orderService = new OrderService();
        this.orderView = new OrderView();
    }

    public void selectOrder_store(int store_id)
    {
        orderView.printOrder(orderService.selectOrder_store(store_id));
    }

    public void deliveryFinish(int store_id)
    {
        List<OrderDTO> od= orderService.selectOrder_store_Delivery(store_id);
        System.out.println("==배달중인 주문 목록==");
        orderView.printOrderWithNumber(od);
        System.out.print("배달완료할 주문번호를 선택해 주세요 : ");
        int inputNum = sc.nextInt();
        int order_id =od.get(inputNum-1).getOrder_id();
        int result = orderService.updateOrderState_Complete(order_id);
        if( result == 1) System.out.println("배달 완료");
        else System.out.println("배달완료 실패");
    }

    public void acceptOrder(int store_id)
    {
        List<OrderDTO> od= orderService.selectOrder_store_Waiting(store_id);
        System.out.println("==접수 대기중인 주문 목록==");
        orderView.printOrderWithNumber(od);
        System.out.print("접수할 주문번호를 선택해 주세요 : ");
        int inputNum = sc.nextInt();
        int order_id =od.get(inputNum-1).getOrder_id();
        System.out.println("주문 접수(1) , 거절(2) : ");
        int inputSelect = sc.nextInt();
        if( inputSelect ==1)
        {
            int result = orderService.updateOrderState_Delivery(order_id);
            if( result == 1) System.out.println("주문 접수 완료");
            else System.out.println("주문 접수 실패");
        }
        else if (inputSelect ==2)
        {
            int result = orderService.updateOrderState_Cancle(order_id);
            if( result == 1) System.out.println("주문 거절 완료");
            else System.out.println("주문 거절 실패");
        }
    }

    public void menuUpdate()
    {

    }
}