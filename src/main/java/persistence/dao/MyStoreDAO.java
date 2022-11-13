package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.StoreDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyStoreDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

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

    public List<StoreDTO> selectAllStoreId(){
        List<StoreDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.StoreMapper.selectAllStoreId");
        } finally {
            session.close();
        }
        return list;
    }

    public List<StoreDTO> selectAllStoreNameAndId(){
        List<StoreDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list  = session.selectList("mapper.StoreMapper.selectAllStoreNameAndId");
        } finally {
            session.close();
        }
        return list;
    }

    public StoreDTO selectById(int id)
    {
        StoreDTO storeDTO;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            storeDTO = session.selectOne("mapper.StoreMapper.selectStore_id", id);
        } finally {
            session.close();
        }
        return storeDTO;
    }

    public void storeAdd(StoreDTO addStoreDTO)
    {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.StoreMapper.storeAdd", addStoreDTO);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
    }
}

