package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.MenuDTO;

import java.util.List;

public class MyMenuDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyMenuDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<MenuDTO> selectAll(){
        List<MenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.MenuMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }

    public List<MenuDTO> selectMenuStoreId(int store_id){
        List<MenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.MenuMapper.selectMenuStoreId", store_id);
        } finally {
            session.close();
        }
        return list;
    }

    public MenuDTO selectByStoreId(String id){
        MenuDTO menuDTO = new MenuDTO();
        SqlSession session = sqlSessionFactory.openSession();

        try{
            menuDTO = session.selectOne("mapper.MenuMapper.selectMenu_StoreId", id);
        } finally {
            session.close();
        }

        return null;
    }

    public void menuAdd(MenuDTO menuDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.selectOne("mapper.MenuMapper.menuAdd", menuDTO);

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

