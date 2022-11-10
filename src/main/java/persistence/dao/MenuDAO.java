package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.MenuDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<MenuDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<MenuDTO> menuDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                MenuDTO menuDTO = new MenuDTO();

                int menu_id = rs.getInt("menu_id");
                int store_id = rs.getInt("store_id");
                Long menu_price = rs.getLong("menu_price");
                int menu_quantity = rs.getInt("menu_quantity");
                String menu_category = rs.getString("menu_category");

                menuDTO.setMenu_id(menu_id);
                menuDTO.setStore_id(store_id);
                menuDTO.setMenu_price(menu_price);
                menuDTO.setMenu_quantity(menu_quantity);
                menuDTO.setMenu_category(menu_category);
                menuDTOs.add(menuDTO);
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
        return menuDTOs;
    }
}
