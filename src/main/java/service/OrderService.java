package service;

import persistence.dao.MyMenuOptionDAO;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyOrderMenuDAO;
import persistence.dto.OrderDTO;
import persistence.dto.OrderMenuDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class OrderService {
    private final MyOrderDAO orderDAO;
    private final MyOrderMenuDAO orderMenuDAO;

    public OrderService()
    {
        orderDAO = new MyOrderDAO();
        orderMenuDAO = new MyOrderMenuDAO();
    }

    public List<OrderDTO> selectAll()
    {
        List<OrderDTO> orderDTOS = orderDAO.selectAll();
        return orderDTOS;
    }

    public int insertOrder(String user_id , int store_id, long order_price, LocalDateTime order_orderTime, int menu_id, String order_num)
    {
        MenuService menuS = new MenuService();
        String orderTime = order_orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        OrderDTO od = new OrderDTO(user_id, store_id, order_price, orderTime, order_num);

        orderDAO.insertOrder(od); //오더테이블에 주문 넣기
        menuS.updateMenuQuantity(menu_id); //메뉴 개수 수정

        return orderDAO.findOrderId(order_num).getOrder_id();
    }

    public int insertOrderMenu(int order_id, String menu_name)
    {
        int orderMenu_id = order_id + 100;
        OrderMenuDTO orderMenuDTO = new OrderMenuDTO(orderMenu_id, order_id, menu_name);
        orderMenuDAO.insertOrderMenu(orderMenuDTO); //오더메뉴 테이블에 메뉴명 넣기
        return orderMenu_id;
    }

    public List<OrderMenuDTO> findOrderMenuId(int order_id)
    {
        return orderMenuDAO.findOrderMenuId(order_id);
    }

    public List<OrderDTO> selectOrder_store(int store_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_store(store_id);

        return orderDTOS;
    }

    public List<OrderDTO> selectOrder_store_Waiting(int store_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_store_Waiting(store_id);

        return orderDTOS;
    }

    public List<OrderDTO> selectOrder_store_Delivery(int store_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_store_Delivery(store_id);

        return orderDTOS;
    }

    public List<OrderDTO> selectOrder_customer(String user_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectOrder_customer(user_id);

        return orderDTOS;
    }

    public List<OrderDTO> selectAllOrder_customer(String user_id)
    {
        List<OrderDTO> orderDTOS = orderDAO.selectAllOrder_customer(user_id);

        return orderDTOS;
    }

    public int updateOrderState_Complete(int order_id)
    {
        OrderDTO orderDTO = new OrderDTO(order_id);
        int result =orderDAO.updateOrderState_Complete(order_id);
        return result;
    }

    public int updateOrderState_Cancle(int order_id)
    {
        OrderDTO orderDTO = new OrderDTO(order_id);
        int result =orderDAO.updateOrderState_Cancle(order_id);
        return result;
    }

    public int updateOrderState_Delivery(int order_id)
    {
        OrderDTO orderDTO = new OrderDTO(order_id);
        int result =orderDAO.updateOrderState_Delivery(order_id);
        return result;
    }




}
