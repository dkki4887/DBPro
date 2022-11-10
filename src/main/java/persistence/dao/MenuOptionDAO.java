package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.MenuOptionDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuOptionDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<MenuOptionDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<MenuOptionDTO> menuOptionDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                MenuOptionDTO menuOptionDTO = new MenuOptionDTO();

                int menu_id = rs.getInt("menu_id");
                int option_id = rs.getInt("option_id");

                menuOptionDTO.setMenu_id(menu_id);
                menuOptionDTO.setOption_id(option_id);
                menuOptionDTOs.add(menuOptionDTO);
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
        return menuOptionDTOs;
    }
}


