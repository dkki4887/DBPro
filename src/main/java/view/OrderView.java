package view;

import persistence.dto.OrderDTO;
import persistence.dto.OrderMenuDTO;
import persistence.dto.OrderOptionDTO;

import java.util.List;

public class OrderView {

    public void printAll(List<OrderDTO> dtos){
        for(OrderDTO dto: dtos){
            System.out.println("dto.toString() = " + dto.toString());
        }
    }

    public void printMyOrder(List<OrderDTO> od){
        for(OrderDTO ods: od){
            System.out.print("고객: " + ods.getUser_id() + ", " + ods.getOrderMenuList() + ", " + ods.getOrderOptionList()
                    + ", " + ods.getOrder_price() + ", " + ods.getOrder_state());
        }
    }
}
