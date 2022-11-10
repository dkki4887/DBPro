package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.OrderDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<OrderDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<OrderDTO> orderDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                OrderDTO orderDTO = new OrderDTO();

                int order_id = rs.getInt("order_id");
                String user_id = rs.getString("user_id");
                int store_id = rs.getInt("store_id");
                long order_price = rs.getLong("order_price");
                String order_payment = rs.getString("order_payment");
                String order_state = rs.getString("order_state");
                LocalDateTime order_orderTime = rs.getTimestamp("order_orderTime").toLocalDateTime();
                LocalDateTime order_receiveTime = rs.getTimestamp("order_receiveTime").toLocalDateTime();

                orderDTO.setOrder_id(order_id);
                orderDTO.setUser_id(user_id);
                orderDTO.setStore_id(store_id);
                orderDTO.setOrder_price(order_price);
                orderDTO.setOrder_payment(order_payment);
                orderDTO.setOrder_state(order_state);
                orderDTO.setOrder_orderTime(order_orderTime);
                orderDTO.setOrder_receiveTime(order_receiveTime);
                orderDTOs.add(orderDTO);
                conn.commit();
            }
        } catch(SQLException e){
            System.out.println("error : " + e);
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        finally{
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return orderDTOs;
    }
}
