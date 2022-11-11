package view;

import persistence.dto.OrderDTO;

import java.util.List;

public class OrderView {
    public void printAll(List<OrderDTO> dtos){
        for(OrderDTO dto: dtos){
            System.out.println("dto.toString() = " + dto.toString());
        }
    }

    public void printMyOrder(List<OrderDTO> dtos){
        for(OrderDTO dto: dtos){
            System.out.println("주문번호 : "+dto.getOrder_id() +"/ 회원"+dto.getUser_id()+"/ 가격: " +dto.getOrder_price()
                    +"/ 주문상태 : " + dto.getOrder_state());
        }
    }
}
