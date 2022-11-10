package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.OptionDTO;

import java.util.List;

public class MyOptionDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyOptionDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
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
    public OptionDTO selectById(Long id){return null;}
}

