package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.StoreMenuDTO;

import java.util.List;

public class MyStoreMenuDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyStoreMenuDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<StoreMenuDTO> selectAll(){
        List<StoreMenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.StoreMenuMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }
    public StoreMenuDTO selectById(Long id){return null;}
}

