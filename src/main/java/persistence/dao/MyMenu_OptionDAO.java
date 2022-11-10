package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.MenuOptionDTO;

import java.util.List;

public class MyMenu_OptionDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyMenu_OptionDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
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
    public MenuOptionDTO selectById(Long id){return null;}
}

