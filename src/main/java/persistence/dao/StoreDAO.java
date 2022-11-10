package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.StoreDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<StoreDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<StoreDTO> storeDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                StoreDTO storeDTO = new StoreDTO();

                int store_id = rs.getInt("store_id");
                String user_id = rs.getString("user_id");
                String store_name = rs.getString("store_name");
                String store_phone = rs.getString("store_phone");
                String store_address = rs.getString("store_address");
                boolean store_state = rs.getBoolean("store_state");
                int store_category = rs.getInt("store_category");
                int store_rate = rs.getInt("store_rate");
                String store_time = rs.getString("store_time");
                String store_info = rs.getString("store_info");

                storeDTO.setStore_id(store_id);
                storeDTO.setUser_id(user_id);
                storeDTO.setStore_name(store_name);
                storeDTO.setStore_phone(store_phone);
                storeDTO.setStore_address(store_address);
                storeDTO.setStore_state(store_state);
                storeDTO.setStore_category(store_category);
                storeDTO.setStore_rate(store_rate);
                storeDTO.setStore_time(store_time);
                storeDTO.setStore_info(store_info);
                storeDTOs.add(storeDTO);
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
        return storeDTOs;
    }
}
