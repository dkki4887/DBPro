package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.StoreMenuDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreMenuDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<StoreMenuDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<StoreMenuDTO> storeMenuDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                StoreMenuDTO storeMenuDTO = new StoreMenuDTO();

                int store_id = rs.getInt("store_id");
                int menu_id = rs.getInt("menu_id");

                storeMenuDTO.setStore_id(store_id);
                storeMenuDTO.setMenu_id(menu_id);
                storeMenuDTOs.add(storeMenuDTO);
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
        return storeMenuDTOs;
    }
}


