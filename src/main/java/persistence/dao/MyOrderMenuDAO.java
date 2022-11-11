package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.OrderMenuDTO;

import java.util.List;

public class MyOrderMenuDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyOrderMenuDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<OrderMenuDTO> selectAll(){
        List<OrderMenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OrderMenuMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }
    public OrderMenuDTO selectById(Long id){return null;}
}

