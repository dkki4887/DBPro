package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.BoardDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyBoardDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public MyBoardDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<BoardDTO> selectAll(){
        List<BoardDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try{
            list = session.selectList("mapper.BoardMapper.selectAll");
        }finally{
            session.close();
        } return list;
    }

}
