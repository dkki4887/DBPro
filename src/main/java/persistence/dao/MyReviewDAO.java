package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.ReviewDTO;
import persistence.dto.Review_omDTO;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MyReviewDAO {
    private final SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

    public List<ReviewDTO> selectAll(){
        List<ReviewDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.ReviewMapper.selectAll");
        }finally {
            session.close();
        }
        return list;
    }

    public int insertReview(ReviewDTO reviewDTO) {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result =session.insert("mapper.ReviewMapper.insertReview" , reviewDTO);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }

        }finally {
            session.close();
        }
        return result;
    }

    public List<Review_omDTO> findReviewWithUserIdLike(String user_id)
    {
        List<Review_omDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.ReviewMapper.findReviewWithUserIdLike",user_id);
        }finally {
            session.close();
        }
        return dtos;
    }

    public List<Review_omDTO> findReviewWithStoreAndNonReply(int store_id)
    {
        List<Review_omDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.ReviewMapper.findReviewWithStoreAndNonReply",store_id);
        }finally {
            session.close();
        }
        return dtos;
    }

    public List<Review_omDTO> findReviewWithStoreAndReply(int store_id)
    {
        List<Review_omDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.ReviewMapper.findReviewWithStoreAndReply",store_id);
        }finally {
            session.close();
        }
        return dtos;
    }

}