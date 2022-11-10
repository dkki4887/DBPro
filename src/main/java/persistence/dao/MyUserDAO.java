package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.UserDTO;

import java.util.List;

public class MyUserDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyUserDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<UserDTO> selectAll(){
        List<UserDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.UserMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }
    public UserDTO selectById(Long id){return null;}
}

