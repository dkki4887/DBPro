package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.OptionDTO;

import java.util.List;

public class MyOptionDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

    public List<OptionDTO> selectAll(){
        List<OptionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OptionMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }

    public List<OptionDTO> selectMenuOption(int menu_id) {
        List<OptionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OptionMapper.selectMenuOption", menu_id);
        } finally {
            session.close();
        }
        return list;
    }

    public List<OptionDTO> selectOptionPrice(int option_id) {
        List<OptionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OptionMapper.selectOptionPrice", option_id);
        } finally {
            session.close();
        }
        return list;
    }
}

