package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.StoreMenuDTO;

import java.util.List;

public class MyStore_MenuDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyStore_MenuDAO(SqlSessionFactory sqlSessionFactory){
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

