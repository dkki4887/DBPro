package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.Order_MenuDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order_MenuDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<Order_MenuDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Order_MenuDTO> orderMenuDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                Order_MenuDTO orderMenuDTO = new Order_MenuDTO();

                int order_id = rs.getInt("order_id");
                int menu_id = rs.getInt("menu_id");
                long menu_price = rs.getLong("menu_price");

                orderMenuDTO.setOrder_id(order_id);
                orderMenuDTO.setMenu_id(menu_id);
                orderMenuDTO.setMenu_price(menu_price);
                orderMenuDTOs.add(orderMenuDTO);
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
        return orderMenuDTOs;
    }
}


