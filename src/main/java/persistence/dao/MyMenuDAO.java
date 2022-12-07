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

    public int selectStoreMenuNum(int store_id)
    {
        int storeMenuNum = 0;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            storeMenuNum = session.selectOne("mapper.MenuMapper.selectStoreMenuNum", store_id);
        } finally {
            session.close();
        }
        return storeMenuNum;
    }

    public int menuAdd(MenuDTO menuDTO){
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
        return menuDTO.getMenu_id();
    }

    public void menuUpdate(MenuDTO menuDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.update("mapper.MenuMapper.menuUpdate", menuDTO);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void updateMenuQuantity(int menu_id)
    {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.MenuMapper.updateMenuQuantity", menu_id);

            if (result == 1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
    }

    public List<MenuDTO> selectMenu_WaitingAccept() {
            List<MenuDTO> list = null;
            SqlSession session = sqlSessionFactory.openSession();
            try {
                list = session.selectList("mapper.MenuMapper.selectMenu_WaitingAccept");
            } finally {
                session.close();
            }
            return list;
        }

        public int updateMenu_Accept(int menu_id) {
            SqlSession session = sqlSessionFactory.openSession();
            int result = -1;
            try {
                result = session.update("mapper.MenuMapper.updateMenu_Accept", menu_id);

                if (result == 1) {
                    session.commit();
                } else {
                    session.rollback();
                }

            } finally {
                session.close();
            }
            return result;
        }

        public int deleteMenu(int menu_id) {
            SqlSession session = sqlSessionFactory.openSession();
            int result = -1;
            try {
                result = session.delete("mapper.MenuMapper.deleteMenu", menu_id);

                if (result == 1) {
                    session.commit();
                } else {
                    session.rollback();
                }
            } finally {
                session.close();
            }
            return result;
        }
}

