package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.OrderOptionDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderOptionDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<OrderOptionDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<OrderOptionDTO> orderOptionDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                OrderOptionDTO orderOptionDTO = new OrderOptionDTO();

                int order_id = rs.getInt("order_id");
                String option_name = rs.getString("option_name");
                long option_price = rs.getLong("option_price");

                orderOptionDTO.setOrder_id(order_id);
                orderOptionDTO.setOption_name(option_name);
                orderOptionDTO.setOption_price(option_price);
                orderOptionDTOs.add(orderOptionDTO);
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
        return orderOptionDTOs;
    }
}



