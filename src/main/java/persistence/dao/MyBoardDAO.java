package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.BoardDTO;

import java.util.List;

public class MyBoardDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyBoardDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<BoardDTO> selectAll(){
        List<BoardDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.BoardMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }
    public BoardDTO selectById(Long id){return null;}
}

