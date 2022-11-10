package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.OptionDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<OptionDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<OptionDTO> optionDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                OptionDTO optionDTO = new OptionDTO();

                int option_id = rs.getInt("option_id");
                long option_price = rs.getLong("option_price");
                String option_name = rs.getString("option_name");

                optionDTO.setOption_id(option_id);
                optionDTO.setOption_price(option_price);
                optionDTO.setOption_name(option_name);
                optionDTOs.add(optionDTO);
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
        return optionDTOs;
    }
}


