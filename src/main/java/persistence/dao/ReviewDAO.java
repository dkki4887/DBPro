package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.ReviewDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private final DataSource ds = PooledDataSource.getDataSource();

    public List<ReviewDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        try{
            String query = "SELECT * FROM BOARD";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                ReviewDTO reviewDTO = new ReviewDTO();

                int review_id = rs.getInt("review_id");
                int store_id = rs.getInt("store_id");
                String user_id = rs.getString("user_id");
                int order_id = rs.getInt("order_id");
                int review_rate = rs.getInt("review_rate");
                String review_content = rs.getString("review_content");
                LocalDateTime review_time = rs.getTimestamp("review_time").toLocalDateTime();

                reviewDTO.setReview_id(review_id);
                reviewDTO.setStore_id(store_id);
                reviewDTO.setUser_id(user_id);
                reviewDTO.setOrder_id(order_id);
                reviewDTO.setReview_rate(review_rate);
                reviewDTO.setReview_content(review_content);
                reviewDTO.setReview_time(review_time);
                reviewDTOs.add(reviewDTO);
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
        return reviewDTOs;
    }
}

