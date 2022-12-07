package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.StoreDTO;
import persistence.dto.StoreReviewDTO;

import java.util.ArrayList;
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

    public List<StoreDTO> selectStoreTime(int store_id){
        List<StoreDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list  = session.selectList("mapper.StoreMapper.selectStoreTime", store_id);
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
            storeDTO = session.selectOne("mapper.StoreMapper.selectStoreByid", id);
        } finally {
            session.close();
        }
        return storeDTO;
    }

    public List<StoreDTO> selectByUserId(String user_id)
    {
        List<StoreDTO> storeDTOs = new ArrayList<StoreDTO>();
        SqlSession session = sqlSessionFactory.openSession();
        try{
            storeDTOs.add(session.selectOne("mapper.StoreMapper.selectByUserid", user_id));
        } finally {
            session.close();
        }
        return storeDTOs;
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

    public List<StoreDTO> selectStore_WaitingAccept(){
        List<StoreDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list  = session.selectList("mapper.StoreMapper.selectStore_WaitingAccept");
        } finally {
            session.close();
        }
        return list;
    }

    public List<StoreDTO> selectStore_Accepted(){
        List<StoreDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list  = session.selectList("mapper.StoreMapper.selectStore_Accepted");
        } finally {
            session.close();
        }
        return list;
    }

    public List<StoreReviewDTO> selectStoreReview(){
        List<StoreReviewDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list  = session.selectList("mapper.StoreMapper.selectStoreReview");
        } finally {
            session.close();
        }
        return list;
    }

    public int updateStore_Accept(int store_id) {

        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result =session.update("mapper.StoreMapper.updateStore_Accept",store_id);

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

    public int updateStoreTime(StoreDTO storeDTO) {

        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result =session.update("mapper.StoreMapper.storeTimeUpdate",storeDTO);

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

    public int deleteStore(int store_id)
    {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.delete("mapper.StoreMapper.deleteStore", store_id);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }
}

