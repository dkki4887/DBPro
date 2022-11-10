package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.UserDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<UserDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<UserDTO> userDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                UserDTO userDTO = new UserDTO();

                String user_id = rs.getString("user_id");
                String user_pw = rs.getString("user_pw");
                String user_name = rs.getString("user_name");
                String user_address = rs.getString("user_address");
                String user_phone = rs.getString("user_phone");
                int user_category = rs.getInt("user_category");

                userDTO.setUser_id(user_id);
                userDTO.setUser_pw(user_pw);
                userDTO.setUser_name(user_name);
                userDTO.setUser_address(user_address);
                userDTO.setUser_phone(user_phone);
                userDTO.setUser_category(user_category);
                userDTOs.add(userDTO);
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
        return userDTOs;
    }
}

