package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.StoreDTO;

import java.util.List;

public class MyStoreDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyStoreDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<StoreDTO> selectAll(){
        List<StoreDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.StoreMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }
    public StoreDTO selectById(Long id){return null;}
}

