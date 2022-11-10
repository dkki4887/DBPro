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
    public MenuDTO selectById(Long id){return null;}
}

