package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.OrderDTO;

import java.util.List;

public class MyOrderDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyOrderDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<OrderDTO> selectAll(){
        List<OrderDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OrderMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }
    public OrderDTO selectById(Long id){return null;}
}

