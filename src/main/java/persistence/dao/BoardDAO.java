package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.BoardDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
    public List<BoardDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String selectQuery = "SELECT * FROM BOARD";
        List<BoardDTO> boardDTOs = new ArrayList<>();

        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            //conn = DriverManager.getConnection(url, "root", "sungryulKim12");
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                BoardDTO boardDTO = new BoardDTO();
                Long id = rs.getLong("board_id");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String content = rs.getString("contents");
                LocalDateTime regDate = rs.getTimestamp("regdate").toLocalDateTime();
                int hit = rs.getInt(6);
                boardDTO.setId(id);
                boardDTO.setTitle(title);
                boardDTO.setWriter(writer);
                boardDTO.setContent(content);
                boardDTO.setRegDate(regDate);
                boardDTO.setHit(hit);
                boardDTOs.add(boardDTO);
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
        return boardDTOs;
    }
}
