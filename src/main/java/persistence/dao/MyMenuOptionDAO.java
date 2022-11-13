package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MenuOptionDTO;

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
    public MenuOptionDTO selectById(Long id){return null;}
}

