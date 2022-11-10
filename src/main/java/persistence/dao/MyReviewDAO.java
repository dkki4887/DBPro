package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.ReviewDTO;

import java.util.List;

public class MyReviewDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyReviewDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<ReviewDTO> selectAll(){
        List<ReviewDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.ReviewMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }
    public ReviewDTO selectById(Long id){return null;}
}

