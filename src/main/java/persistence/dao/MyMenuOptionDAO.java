package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MenuOptionDTO;
import persistence.dto.OptionDTO;

import java.util.List;

public class MyMenuOptionDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

    public List<MenuOptionDTO> selectAll(){
        List<MenuOptionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.MenuOptionMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }

    public List<MenuOptionDTO> selectMenuOptionId(int menu_id) {
        List<MenuOptionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.MenuOptionMapper.selectMenuOptionId", menu_id);
        } finally {
            session.close();
        }
        return list;
    }

    public void menuOptionAdd(List<MenuOptionDTO> menuOptionDTOs){
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            for(MenuOptionDTO addMenuOption : menuOptionDTOs) {
                result = session.insert("mapper.MenuOptionMapper.menuOptionAdd", addMenuOption);
            }
            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
    }

    public int deleteMenuOption(MenuOptionDTO menuOptionDTO)
    {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.delete("mapper.MenuOptionMapper.deleteMenuOption", menuOptionDTO);
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

