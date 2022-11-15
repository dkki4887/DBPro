package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MenuDTO;
import persistence.dto.StoreDTO;

import java.util.List;

public class MyMenuDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

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

    public List<MenuDTO> selectStoreMenu(int store_id){
        List<MenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.MenuMapper.selectStoreMenu", store_id);
        } finally {
            session.close();
        }
        return list;
    }

    public List<MenuDTO> selectMenuCategoryList(int store_id){
        List<MenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.MenuMapper.selectMenuCategoryList", store_id);
        } finally {
            session.close();
        }
        return list;
    }

    public MenuDTO selectMenuById(int menu_id){
        MenuDTO menuDTO = new MenuDTO();
        SqlSession session = sqlSessionFactory.openSession();

        try{
            menuDTO = session.selectOne("mapper.MenuMapper.selectMenuById", menu_id);
        } finally {
            session.close();
        }
        return menuDTO;
    }

    public List<MenuDTO> selectAllMenuId(){
        List<MenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.MenuMapper.selectAllMenuId");
        } finally {
            session.close();
        }
        return list;
    }

    public void menuAdd(MenuDTO menuDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.MenuMapper.menuAdd", menuDTO);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void menuUpdate(MenuDTO menuDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.MenuMapper.menuUpdate", menuDTO);

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

